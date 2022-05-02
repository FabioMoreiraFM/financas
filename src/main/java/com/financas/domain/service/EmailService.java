package com.financas.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.financas.domain.model.Email;

@Service
public class EmailService {

	@Autowired
	private MailSender mailSender;
	
	public void enviarEmail(Email email) {
		SimpleMailMessage simpleMailMessage = configurarEmail(email);
		
		mailSender.send(simpleMailMessage);
	}
	
	public SimpleMailMessage configurarEmail(Email email) {
		SimpleMailMessage novoEmail = new SimpleMailMessage();
		novoEmail.setFrom(email.getFrom());
		novoEmail.setTo(email.getTo());
		novoEmail.setSubject(email.getTitulo());
		novoEmail.setText(email.getCorpoEmail());
		
		return novoEmail;
	}
	
}
