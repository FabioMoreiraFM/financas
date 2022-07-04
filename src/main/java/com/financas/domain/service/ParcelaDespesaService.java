package com.financas.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.api.model.input.ListaParcelaDespesaInputModel;
import com.financas.domain.exception.EntidadeNaoEncontradaException;
import com.financas.domain.exception.EnumEntidadeException;
import com.financas.domain.exception.OperacaoJaEfetuadaException;
import com.financas.domain.exception.SolicitacaoInconsistenteException;
import com.financas.domain.model.Despesa;
import com.financas.domain.model.ParcelaDespesa;
import com.financas.domain.repository.ParcelaDespesaRepository;
import com.financas.infrastructure.utils.DataUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ParcelaDespesaService {

	@Autowired
	private ParcelaDespesaRepository parcelaDespesaRepository;

	public ParcelaDespesa buscar(Long parcelaId) {
		return parcelaDespesaRepository.findById(parcelaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.Parcelas, parcelaId));
	}
	
	public List<ParcelaDespesa> gerar(Despesa despesa) {
		Integer totalParcelas = despesa.getTotalParcelas();
		BigDecimal valorParcela = despesa.getValorParcela();
		Integer diaVencimentoParcela = despesa.getDiaVencimentoParcela();
		LocalDate dtVencimento = configurarDtVencimentoParcela(despesa.getMesAnoInicioPgto(), diaVencimentoParcela);
		
		List<ParcelaDespesa> parcelas = new ArrayList<ParcelaDespesa>();
		for (int nParcela = 1; nParcela <= totalParcelas; nParcela++) {
			ParcelaDespesa novaParcela = gerarNovaParcela(despesa, valorParcela, dtVencimento, nParcela);
			
			parcelas.add(novaParcela);
			
			dtVencimento = dtVencimento.plusMonths(1);
			dtVencimento = configurarDtVencimentoParcela(dtVencimento, diaVencimentoParcela); 
		}

		log.info("{} parcelas geradas para a despesa de id {}", parcelas.size(), despesa.getId());
		
		return parcelas;
	}

	private ParcelaDespesa gerarNovaParcela(Despesa despesa, BigDecimal valorParcela, LocalDate dtVencimento, int nParcela) {
		ParcelaDespesa novaParcela = ParcelaDespesa.builder()
				.valor(valorParcela)
				.numeroParcela(nParcela)
				.dtVencimento(dtVencimento)
				.despesa(despesa)
				.build();
		return novaParcela;
	}

	private LocalDate configurarDtVencimentoParcela(LocalDate dtVencimento, Integer diaVencimentoParcela) {
		LocalDate novaDtVencimento = dtVencimento
				.withDayOfMonth(Math.min(dtVencimento.lengthOfMonth(), diaVencimentoParcela));
		
		novaDtVencimento = DataUtils.getDiaUtilMaisProximo(novaDtVencimento);
		return novaDtVencimento;
	}
	
	public ParcelaDespesa salvar(ParcelaDespesa parcelaDespesa) {
		log.info("Salvando parcela de id {}", parcelaDespesa.getId());
		
		return parcelaDespesaRepository.save(parcelaDespesa);
	}
	
	public List<ParcelaDespesa> salvar(List<ParcelaDespesa> parcelasDespesa) {
		log.info("Salvando um total de {} parcelas da despesa {}", parcelasDespesa.size(), parcelasDespesa.get(0).getDespesa().getId());
		
		return parcelaDespesaRepository.saveAll(parcelasDespesa);
	}

	public List<ParcelaDespesa> getParcelasSemAgendamento(LocalDate mesPesquisa) {
		YearMonth mes = YearMonth.from(mesPesquisa);
		LocalDate dtInicial = mes.atDay(1);
		LocalDate dtFinal = mes.atEndOfMonth();
		
		return parcelaDespesaRepository.findByAgendaIsNullAndDtVencimentoBetween(dtInicial, dtFinal);
	}

	public void baixar(ParcelaDespesa parcelaApagar, LocalDate dtPagamento) {
		parcelaApagar.setDtPagamento(dtPagamento);		
		
		parcelaDespesaRepository.save(parcelaApagar);
		
		log.info("Parcela de id {} foi baixada com data {}", parcelaApagar.getId(), dtPagamento.toString());
	}

	@Transactional
	public void baixarEmLote(Despesa despesas, ListaParcelaDespesaInputModel parcelasProcuradas) {
		log.info("Iniciando processo de baixa em lote de {} parcelas", parcelasProcuradas.getParcelas().size());
		
		List<ParcelaDespesa> parcelasABaixar = new ArrayList<ParcelaDespesa>();
		
		parcelasProcuradas.getParcelas()
			.forEach(parcelaProcurada -> {
				ParcelaDespesa parcelaABaixar = getParcelaABaixar(despesas, parcelaProcurada.getIdParcela());
				parcelaABaixar.setDtPagamento(parcelaProcurada.getDtPagamento());
				
				parcelasABaixar.add(parcelaABaixar);
			});
		
		parcelaDespesaRepository.saveAll(parcelasABaixar);
		log.info("{} parcelas baixadas da despesa de id {}", parcelasABaixar.size(), despesas.getId());
	}

	public ParcelaDespesa getParcelaABaixar(Despesa despesas, Long idParcelaProcurada) {
		ParcelaDespesa parcelaABaixar = despesas.getParcelas()
			.stream()
			.filter(parcela -> parcela.getId() == idParcelaProcurada)
			.findAny()
			.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.Parcelas, idParcelaProcurada));
		
		if (parcelaABaixar.getDtPagamento() != null) {
			log.info("Parcela de id {} já foi baixada", idParcelaProcurada);
			throw new OperacaoJaEfetuadaException(EnumEntidadeException.Parcelas, parcelaABaixar.getId());
		}
		
		return parcelaABaixar;
	}
	
	public ParcelaDespesa getParcelaPaga(Despesa despesas, Long idParcelaProcurada) {
		ParcelaDespesa parcelaEmAberto = despesas.getParcelas()
			.stream()
			.filter(parcela -> parcela.getId() == idParcelaProcurada)
			.findAny()
			.orElseThrow(() -> new EntidadeNaoEncontradaException(EnumEntidadeException.Parcelas, idParcelaProcurada));
		
		if (parcelaEmAberto.getDtPagamento() == null) {
			log.info("Parcela de id {} não foi baixada.", idParcelaProcurada);
			throw new SolicitacaoInconsistenteException(EnumEntidadeException.Parcelas, String.format("A parcela de id {} não foi baixada.", parcelaEmAberto.getId()));
		}
		
		return parcelaEmAberto;
	}
	
}
