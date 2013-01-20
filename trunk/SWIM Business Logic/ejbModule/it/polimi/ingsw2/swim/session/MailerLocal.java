package it.polimi.ingsw2.swim.session;

import it.polimi.ingsw2.swim.session.Mailer.MessageType;

import java.util.Map;

interface MailerLocal {

	void sendApplicationEmail(String to, MessageType type,
			Map<String, String> parameters);

	void sendEmail(String to, String subject, String text);

}
