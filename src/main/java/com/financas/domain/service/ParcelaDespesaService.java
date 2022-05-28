package com.financas.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financas.domain.model.Despesa;
import com.financas.domain.model.ParcelaDespesa;
import com.financas.domain.repository.ParcelaDespesaRepository;
import com.financas.infrastructure.utils.DataUtils;

@Service
public class ParcelaDespesaService {

	@Autowired
	private ParcelaDespesaRepository parcelaDespesaRepository;

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
		return parcelaDespesaRepository.save(parcelaDespesa);
	}
	
	public List<ParcelaDespesa> salvar(List<ParcelaDespesa> parcelasDespesa) {		
		return parcelaDespesaRepository.saveAll(parcelasDespesa);
	}

	public List<ParcelaDespesa> getParcelasSemAgendamento(LocalDate mesPesquisa) {
		YearMonth mes = YearMonth.from(mesPesquisa);
		LocalDate dtInicial = mes.atDay(1);
		LocalDate dtFinal = mes.atEndOfMonth();
		
		return parcelaDespesaRepository.findByAgendaIsNullAndDtVencimentoBetween(dtInicial, dtFinal);
	}
	
}
