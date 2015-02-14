package com.imanage.intimate;

import org.springframework.mail.javamail.JavaMailSender;

public interface Intimator {
	public void intimateOwner(EmailBean emailBean, JavaMailSender mailSenderImpl);
	public void intimateMember(Long mobileNo, String text);
}
