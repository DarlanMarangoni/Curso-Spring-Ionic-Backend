package com.dmarangoni.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.dmarangoni.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
