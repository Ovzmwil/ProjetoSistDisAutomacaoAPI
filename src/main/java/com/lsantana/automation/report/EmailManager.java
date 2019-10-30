package com.lsantana.automation.report;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.lsantana.automation.util.ConfigFileReader;

public class EmailManager {

	ConfigFileReader reader = new ConfigFileReader("configs/config.properties");

	public void sendEmail(String reportPath, String text) {
		Properties prop = System.getProperties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.host", reader.getPropertyByKey("smtpServer"));
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.debug", "false");
		prop.put("mail.smtp.ssl.trust", reader.getPropertyByKey("smtpServer"));
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(reader.getPropertyByKey("email.user"),
						reader.getPropertyByKey("email.password"));
			}
		});

		Message msg = new MimeMessage(session);

		try {
			msg.setFrom(new InternetAddress(reader.getPropertyByKey("user.email")));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(reader.getPropertyByKey("email.to"), false));
			msg.setContent(setTextAndAttach("", ""));

			Transport transport = session.getTransport();

			transport.connect();
			Transport.send(msg);
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public MimeMultipart setTextAndAttach(String text, String filePath) {
		MimeBodyPart p1 = new MimeBodyPart();
		MimeBodyPart p2 = new MimeBodyPart();
		FileDataSource dataSource = new FileDataSource(filePath);
		Multipart mp = new MimeMultipart();

		try {
			p1.setText(text);
			p2.setDataHandler(new DataHandler(dataSource));
			p2.setFileName(dataSource.getName());

			mp.addBodyPart(p1);
			mp.addBodyPart(p2);

			return (MimeMultipart) mp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
