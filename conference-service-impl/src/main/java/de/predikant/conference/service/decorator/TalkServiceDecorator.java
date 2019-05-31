package de.predikant.conference.service.decorator;

import de.predikant.conference.service.api.TalkService;
import de.predikant.conference.service.exception.RoomNotAvailableException;
import de.predikant.conference.service.exception.TalkNotFoundException;
import de.predikant.conference.service.model.Talk;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class TalkServiceDecorator implements TalkService {

	@Inject
	@Delegate
	@Any
	private TalkService talkService;


	@Override
	public void createTalk(Talk talk) throws RoomNotAvailableException {
		sendJmsStirngMessage("Talk Created: " + talk);
		talkService.createTalk(talk);
	}

	@Override
	public Talk updateTalk(Talk talk) throws RoomNotAvailableException,
			TalkNotFoundException {
		sendJmsStirngMessage("Talk Updated: " + talk);
		return talkService.updateTalk(talk);
	}

	@Override
	public void deleteTalk(long id) throws TalkNotFoundException {
		sendJmsStirngMessage("Talk with id deleted: " + id );
		talkService.deleteTalk(id);
	}

	private void sendJmsStirngMessage(String message) {
		try {


        } catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
