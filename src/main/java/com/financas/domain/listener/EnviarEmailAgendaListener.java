package com.financas.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.financas.domain.listener.event.AgendaEvent;
import com.financas.domain.model.Agenda;
import com.financas.domain.service.EmailService;

@Component
public class EnviarEmailAgendaListener {
	
	@Autowired
	private EmailService emailService;
	
	@EventListener
	public void handleAgendaEnvioEmail(AgendaEvent eventoAgenda) {
		Agenda agenda = (Agenda) eventoAgenda.getSource();
		
		emailService.enviarEmail(agenda.getEmail());
	}
	
}
