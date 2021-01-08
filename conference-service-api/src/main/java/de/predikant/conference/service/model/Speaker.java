package de.predikant.conference.service.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "speaker")
@NamedQueries({
        @NamedQuery(name = "Speaker.findSpeakerById", query = "SELECT s FROM Speaker s WHERE s.id = :id"),
        @NamedQuery(name = "Speaker.findSpeakersByName", query = "SELECT s FROM Speaker s WHERE s.name = :name"),
        @NamedQuery(name = "Speaker.findAll", query = "SELECT s FROM Speaker s"),
})
public class Speaker implements Serializable {

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

}
