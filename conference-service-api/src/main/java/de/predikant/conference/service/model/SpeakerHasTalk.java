package de.predikant.conference.service.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "speaker_has_talk")
@NamedQueries({
        @NamedQuery(name = "SpeakerHasTalk.findTalksBySpeaker", query = "SELECT sht.talk FROM SpeakerHasTalk sht WHERE sht.speaker = :speaker"),
        @NamedQuery(name = "SpeakerHasTalk.findSpeakersByTalk", query = "SELECT sht.speaker FROM SpeakerHasTalk sht WHERE sht.talk = :talk"),
        @NamedQuery(name = "SpeakerHasTalk.findSpeakerHasTalkBySpeakerAndTalk", query = "SELECT sht FROM SpeakerHasTalk sht WHERE sht.speaker.id = :speakerId AND sht.talk.id = :talkId"),})
public class SpeakerHasTalk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "talk_id")
    private Talk talk;

}
