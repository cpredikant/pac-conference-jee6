package de.predikant.conference.service.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "conference")
@NamedQueries({
        @NamedQuery(name = "Conference.findConferenceById", query = "SELECT c FROM Conference c WHERE c.id = :id"),
        @NamedQuery(name = "Conference.findConferenceByName", query = "SELECT c FROM Conference c WHERE c.name = :name"),
        @NamedQuery(name = "Conference.findAll", query = "SELECT c FROM Conference c"),
})
public class Conference implements Serializable {

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

    @NotNull
    @Future
    private LocalDateTime start;

    @NotNull
    @Future
    private LocalDateTime end;

}
