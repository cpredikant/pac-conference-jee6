package de.predikant.conference.service.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "room")
@NamedQueries({
        @NamedQuery(name = "Room.findRoomById", query = "SELECT r FROM Room r WHERE r.id = :id"),
        @NamedQuery(name = "Room.findRoomByName", query = "SELECT r FROM Room r WHERE r.name = :name"),
        @NamedQuery(name = "Room.findRoomsByConferenceId", query = "SELECT r FROM Room r WHERE r.conference.id = :id"),
        @NamedQuery(name = "Room.findAll", query = "SELECT r FROM Room r"),
})
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @Min(1)
    private int capacity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "conference_id")
    private Conference conference;

}
