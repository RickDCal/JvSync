package br.com.jvsync.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

public class EmailTools {

	// for example, smtp.mailgun.org
	private static final String SMTP_SERVER = "smtp.gmail.com";
	private static final String USERNAME = "ricardo12602@gmail.com";
	private static final String PASSWORD = "Rickdias";

	private static final String EMAIL_FROM = "ricardo12602@gmail.com";
	private static final String EMAIL_TO = "ricardo1260@yahoo.com.br";
	private static final String EMAIL_TO_CC = "";

	private static final String EMAIL_SUBJECT = "Test Send Email via SMTP (HTML)";
	private static final String EMAIL_TEXT = "<h1>Hello Java Mail \n ABC123</h1>";

	public void enviaEmail() {
		
		String host = "relay.jangosmtp.net";
		Properties prop = System.getProperties();
		//Properties props = new Properties();
	      prop.put("mail.smtp.auth", "true");
	      prop.put("mail.smtp.starttls.enable", "true");
	      prop.put("mail.smtp.host", host);
	      prop.put("mail.smtp.port", "25");

		Session session = Session.getInstance(prop, null);
		Message msg = new MimeMessage(session);

		try {

			msg.setFrom(new InternetAddress(EMAIL_FROM));

			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(EMAIL_TO, false));

			msg.setSubject(EMAIL_SUBJECT);

			// TEXT email
			//msg.setText(EMAIL_TEXT);

			// HTML email
			msg.setDataHandler(new DataHandler(new HTMLDataSource(EMAIL_TEXT)));


			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

			// connect
			t.connect(SMTP_SERVER, USERNAME, PASSWORD);

			// send
			t.sendMessage(msg, msg.getAllRecipients());

			System.out.println("Response: " + t.getLastServerResponse());

			t.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	static class HTMLDataSource implements DataSource {

		private String html;

		public HTMLDataSource(String htmlString) {
			html = htmlString;
		}

		@Override
		public InputStream getInputStream() throws IOException {
			if (html == null) throw new IOException("html message is null!");
			return new ByteArrayInputStream(html.getBytes());
		}

		@Override
		public OutputStream getOutputStream() throws IOException {
			throw new IOException("This DataHandler cannot write HTML");
		}

		@Override
		public String getContentType() {
			return "text/html";
		}

		@Override
		public String getName() {
			return "HTMLDataSource";
		}
	}
}


