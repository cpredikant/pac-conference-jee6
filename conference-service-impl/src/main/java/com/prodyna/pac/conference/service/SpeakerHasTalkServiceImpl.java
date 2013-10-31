package com.prodyna.pac.conference.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;

import com.prodyna.pac.conference.api.SpeakerHasTalkService;
import com.prodyna.pac.conference.common.util.DateUtil;
import com.prodyna.pac.conference.exception.SpeakerNotAvailableException;
import com.prodyna.pac.conference.model.Speaker;
import com.prodyna.pac.conference.model.SpeakerHasTalk;
import com.prodyna.pac.conference.model.Talk;

@Stateless
public class SpeakerHasTalkServiceImpl implements SpeakerHasTalkService,
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;

	@Inject
	private EntityManager em;

	@Override
	public void assign(Speaker speaker, Talk talk)
			throws SpeakerNotAvailableException {
		SpeakerHasTalk sht = findSpeakerHasTalkBySpeakerAndTalk(speaker, talk);

		if (sht != null) {
			log.info("SpeakerHasTalk already exists");
		} else {

			speakerIsAvailable(speaker, talk);

			sht = new SpeakerHasTalk();
			sht.setSpeaker(speaker);
			sht.setTalk(talk);
			em.persist(sht);
		}

	}

	private void speakerIsAvailable(Speaker speaker, Talk talk) throws SpeakerNotAvailableException {
		List<Talk> talks = findTalksBySpeaker(speaker);

		Date start = talk.getStart();

		Date end = DateUtil.addMinutesToDate(start, talk.getDuration());

		for (Talk t : talks) {

			if (start.after(DateUtil.addMinutesToDate(t.getStart(),
					t.getDuration()))
					|| end.before(t.getStart())) {
				throw new SpeakerNotAvailableException("Speaker "
						+ speaker.getName() + " is not available for talk "
						+ talk.getName());
			}
		}

	}
	

	@Override
	public SpeakerHasTalk findSpeakerHasTalkBySpeakerAndTalk(Speaker speaker,
			Talk talk) {

		TypedQuery<SpeakerHasTalk> query = em.createNamedQuery(
				"SpeakerHasTalk.findSpeakerHasTalkBySpeakerAndTalk",
				SpeakerHasTalk.class);
		query.setParameter("speaker", speaker);
		query.setParameter("talk", talk);

		SpeakerHasTalk speakerHasTalk = null;

		try {
			speakerHasTalk = query.getSingleResult();
		} catch (NoResultException exception) {
			log.info("No result for Entity {} for Speaker {} and Talk {}",
					new Object[] { SpeakerHasTalk.class.getName(), speaker,
							talk });
		}

		return speakerHasTalk;

	}

	@Override
	public void unassign(Speaker speaker, Talk talk) {

		SpeakerHasTalk sht = findSpeakerHasTalkBySpeakerAndTalk(speaker, talk);

		if (sht == null) {
			log.info("SpeakerHasTalk does not exist");
		} else {
			em.remove(sht);
		}

	}

	@Override
	public void unassignTalksBySpeaker(Speaker speaker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unassignSpeakersByTalk(Talk talk) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Speaker> findSpeakersByTalk(Talk talk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Talk> findTalksBySpeaker(Speaker speaker) {

		TypedQuery<Talk> query = em.createNamedQuery(
				"SpeakerHasTalk.findTalksBySpeaker", Talk.class);
		query.setParameter("speaker", speaker);

		return query.getResultList();
	}
	

}
