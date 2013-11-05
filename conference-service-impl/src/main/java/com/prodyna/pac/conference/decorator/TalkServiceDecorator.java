package com.prodyna.pac.conference.decorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

import org.slf4j.Logger;

import com.prodyna.pac.conference.api.TalkService;
import com.prodyna.pac.conference.exception.RoomNotAvailableException;
import com.prodyna.pac.conference.exception.TalkNotFoundException;
import com.prodyna.pac.conference.model.Talk;

@Decorator
public abstract class TalkServiceDecorator implements TalkService {

	@Inject
	@Delegate
	@Any
	private TalkService talkService;

	@Inject
	private Logger logger;

	@Inject
	private InitialContext ctx;

	@Inject
	private QueueConnectionFactory qcf;


	@Override
	public void createTalk(Talk talk) throws RoomNotAvailableException {
		sendJmsStirngMessage("Create");
		talkService.createTalk(talk);
	}

	@Override
	public Talk updateTalk(Talk talk) throws RoomNotAvailableException,
			TalkNotFoundException {
		sendJmsStirngMessage("Update");
		return talkService.updateTalk(talk);
	}

	@Override
	public void deleteTalk(Talk talk) throws TalkNotFoundException {
		sendJmsStirngMessage("Delete");
		talkService.deleteTalk(talk);
	}

	private void sendJmsStirngMessage(String message) {
		try {
			Queue queue = (Queue) ctx.lookup("queue/test");

			Connection connection = qcf.createConnection();
			connection.start();

			Session session = connection.createSession(true,
					Session.AUTO_ACKNOWLEDGE);

			MessageProducer messageProducer = session.createProducer(queue);
			TextMessage textMessage = session
					.createTextMessage("Hallo JMS-Welt");
			messageProducer.send(textMessage);
			logger.info("Senden von JMS-Message {}", textMessage.getText());
			messageProducer.close();

			session.commit();
			session.close();

			connection.stop();
			connection.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
