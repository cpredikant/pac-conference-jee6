package de.predikant.conference.service.api;

import de.predikant.conference.service.exception.ConferenceNotFoundException;
import de.predikant.conference.service.model.Conference;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ConferenceService {

    void createConference(Conference conference);

    Conference updateConference(Conference conference) throws ConferenceNotFoundException;

    void deleteConference(Long id) throws ConferenceNotFoundException;

    Conference findConferenceById(Long id) throws ConferenceNotFoundException;

    List<Conference> findConferenceByName(String name);

    List<Conference> findAll();

}
