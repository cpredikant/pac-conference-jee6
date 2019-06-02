package de.predikant.conference.service;

import de.predikant.conference.service.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {

    List<Conference> findAllByName(String name);

}

