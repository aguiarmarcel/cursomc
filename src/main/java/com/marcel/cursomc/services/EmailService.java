package com.marcel.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.marcel.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfigurationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
