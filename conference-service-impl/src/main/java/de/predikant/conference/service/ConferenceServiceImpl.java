package de.predikant.conference.service;

import de.predikant.conference.service.api.ConferenceService;
import de.predikant.conference.service.interceptor.Logging;
import de.predikant.conference.service.interceptor.Performance;
import de.predikant.conference.service.model.Conference;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
@Performance
@Logging
public class ConferenceServiceImpl implements ConferenceService, Serializable {

    private static final long serialVersionUID = 1L;


    @Inject
    private ConferenceRepository conferenceRepository;


    @Override
    public void createConference(Conference conference) {
        conferenceRepository.save(conference);
    }

    @Override
    public Conference updateConference(Conference conference) {

        return conferenceRepository.save(conference);
    }

    @Override
    public void deleteConference(Long id) {

        conferenceRepository.deleteById(id);
    }

    @Override
    public Conference findConferenceById(Long id) {

        return conferenceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Conference> findConferenceByName(String name) {
        return conferenceRepository.findAllByName(name);
    }

    @Override
    public List<Conference> findAll() {

        return conferenceRepository.findAll();
    }

}
