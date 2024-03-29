package com.financas.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.financas.domain.listener.event.AgendaEvent;
import com.financas.domain.model.Agenda;
import com.financas.domain.model.Email;
import com.financas.domain.model.ParcelaDespesa;
import com.financas.domain.model.Regras;
import com.financas.domain.repository.AgendaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AgendaService {

	@Autowired
	private ParcelaDespesaService parcelaDespesaService;
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private RegrasService regrasService;
	
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	
	@Scheduled(cron = "0 0 23 * * *")
	public void agendar() {
		List<ParcelaDespesa> parcelasDoMesSemAgendamento = parcelaDespesaService.getParcelasSemAgendamento(LocalDate.now());
		
		List<Agenda> agenda = parcelasDoMesSemAgendamento.stream()
			.map(this::gerar)
			.collect(Collectors.toList());
		
		agendaRepository.saveAll(agenda);
		
		log.info("Gerados {} lembretes de pagamentos.", parcelasDoMesSemAgendamento.size());
	}
	
	public Agenda gerar(ParcelaDespesa parcela) {
		Email novoEmail = Email.builder()
				.titulo("[AUTOMÁTICO] Sua despesa está próxima do vencimento!")
				.corpoEmail("Olá, [fulano]. Sua cobrança com vencimento em [x] está próxima, não esqueça de efetuar o pagamento!")
				.to("fabiomoreira.mf@gmail.com")
				.from("fabiomoreira.mf@gmail.com")
				.build();
		
		Agenda agenda = Agenda.builder()
				.dtPrimeiroEnvio(parcela.getDtVencimento().minusDays(3))
				.enviosRealizados(0)
				.envioFinalizado(false)
				.email(novoEmail)
				.build();
		
		parcela.setAgenda(agenda);
		
		return agenda;
	}
	
	@Scheduled(cron = "0 0 7 * * *")
	public void enviarAvisoDtVencimentoProxima() {
		Regras regras = regrasService.buscar(1L);
		
		Integer qtdMaxEnvio = regras.getQtdMaxEnvioAgendamento();
		
		List<Agenda> agendas = agendaRepository.findBydtPrimeiroEnvioAndEnvioFinalizado(LocalDate.now(), false);	
		
		agendas.stream()
			.forEach(agenda -> {
				enviar(agenda);
				atualizarSituacaoAgenda(agenda, qtdMaxEnvio);
			});
		
		log.info("{} lembretes de pagamento foram enviados por e-mail", agendas.size());
	}
	
	public void enviar(Agenda agenda) {
		AgendaEvent agendaEvent = new AgendaEvent(agenda);
		applicationEventPublisher.publishEvent(agendaEvent);
	}
		
	private void atualizarSituacaoAgenda(Agenda agenda, Integer qtdMaxEnvio) {
		agenda.atualizarEnvios();
		if (agenda.getEnviosRealizados() == qtdMaxEnvio) {
			agenda.finalizarEnvios(qtdMaxEnvio);
		} 
	}
}
