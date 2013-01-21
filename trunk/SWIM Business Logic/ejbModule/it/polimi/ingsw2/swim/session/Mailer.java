package it.polimi.ingsw2.swim.session;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Session Bean implementation class Mailer
 */
@Stateless
@Local
public class Mailer implements MailerLocal {

	private static final String TOKEN = "[^\\\\]#";
	private static final Pattern customTokenPattern = Pattern.compile(TOKEN
			+ "(\\w+)");

	public enum Protocol {
		SMTP, SMTPS, TLS
	}

	public enum MessageType {
		REGISTRATION_CONFIRMATION(
				"Conferma la tua registrazione a SWIM.",
				"Grazie #userFirstname per esserti registrato su SWIMv2, \n"
						+ "Per completare la registrazione, conferma la tua identià verificando il tuo indirizzo e-mail. \n"
						+ "Segui questo link: http://" + Mailer.domain
						+ "/userActivation?id=#id&tk=#activationToken"), 
		PASSWORD_RECOVERY(
				"Recupera la tua password su SWIM.",
				"Hai richiesto di recuperare la tua password. \n "
						+ "Per accedere al sistema puoi usare questa password temporanea: #temp \n"
						+ "Ti invitiamo a reimpostare la password nonappena effettuato l'accesso,\n"
						+ "poichè questa password temporanea verrà disabilitata al primo login!");

		public final String baseSubject;
		public final String baseText;
		public final Set<String> parameters;

		MessageType(String object, String baseText) {
			this.baseSubject = object;
			this.baseText = baseText;
			Set<String> tempParameters = new HashSet<String>();
			Matcher tokenFinder = customTokenPattern.matcher(baseSubject);
			while (tokenFinder.find()) {
				tempParameters.add(tokenFinder.group(1));
			}
			tokenFinder.reset(baseText);
			while (tokenFinder.find()) {
				tempParameters.add(tokenFinder.group(1));
			}

			this.parameters = Collections.unmodifiableSet(tempParameters);
		}

	}

	@Resource
	private static String domain;

	@Resource
	private static int port;
	@Resource
	private static String host;
	@Resource
	private static String from;
	@Resource
	private static boolean auth;
	@Resource
	private static String username;
	@Resource
	private static String password;
	@Resource
	private static String protocol;
	@Resource
	private static boolean debug;

	/**
	 * Default constructor.
	 */
	public Mailer() {
	}

	@Override
	public void sendApplicationEmail(String to, MessageType type,
			Map<String, String> parameters) {
		if (!type.parameters.equals(parameters.keySet())) {
			throw new IllegalArgumentException();
		}
		String subject = new String(type.baseSubject);
		String text = new String(type.baseText);
		for (Entry<String, String> param : parameters.entrySet()) {
			subject.replaceAll(TOKEN + param.getKey(), param.getValue());
			text.replaceAll(TOKEN + param.getKey(), param.getValue());
		}
		sendEmail(to, subject, text);
	}

	@Override
	public void sendEmail(String to, String subject, String text) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		switch (Protocol.valueOf(protocol)) {
		case SMTPS:
			props.put("mail.smtp.ssl.enable", true);
			break;
		case TLS:
			props.put("mail.smtp.starttls.enable", true);
			break;
		}
		Authenticator authenticator = null;
		if (auth) {
			props.put("mail.smtp.auth", true);
			authenticator = new Authenticator() {
				private PasswordAuthentication pa = new PasswordAuthentication(
						username, password);

				@Override
				public PasswordAuthentication getPasswordAuthentication() {
					return pa;
				}
			};
		}
		Session session = Session.getInstance(props, authenticator);
		session.setDebug(debug);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			message.setRecipients(Message.RecipientType.TO, address);
			message.setSubject(subject);
			message.setSentDate(new Date());
			message.setText(text);
			Transport.send(message);
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}
}
