package de.predikant.conference.service.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "talk")
@NamedQueries({
        @NamedQuery(name = "Talk.findTalkById", query = "SELECT t FROM Talk t WHERE t.id = :id"),
        @NamedQuery(name = "Talk.findTalkByName", query = "SELECT t FROM Talk t WHERE t.name = :name"),
        @NamedQuery(name = "Talk.findTalksByConferenceId", query = "SELECT t FROM Talk t WHERE t.conference.id = :id"),
        @NamedQuery(name = "Talk.findTalksByRoomId", query = "SELECT t FROM Talk t WHERE t.room.id = :id"),
        @NamedQuery(name = "Talk.findAll", query = "SELECT t FROM Talk t"),})
public class Talk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    @Lob
    @Size(min = 1)
    private String description;

    @Min(1)
    private int duration;

    @NotNull
    @Future
    private LocalDateTime start;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @AssertTrue(message = "Talk is not in daterange of the Conference")
    private boolean isInConferenceDateRange() {

        if (conference == null || conference.getStart() == null
                || conference.getEnd() == null) {
            return true;
        }

        LocalDateTime conferenceStart = conference.getStart();
        LocalDateTime conferenceEnd = conference.getEnd();
        LocalDateTime end = start.plusMinutes(duration);

        return conferenceStart.isBefore(start) && conferenceEnd.isAfter(end);
    }

}
