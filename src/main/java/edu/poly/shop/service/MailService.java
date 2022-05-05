package edu.poly.shop.service;

import javax.mail.MessagingException;

import org.springframework.scheduling.annotation.Scheduled;

import edu.poly.shop.model.MailInfor;

public interface MailService {

	void send(String to, String subject, String body) throws MessagingException;

	void send(MailInfor mail) throws MessagingException;

	void run();

	void queue(String to, String subject, String body);

	void queue(MailInfor mail);

}
