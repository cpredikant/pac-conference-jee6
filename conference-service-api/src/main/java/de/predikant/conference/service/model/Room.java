package de.predikant.conference.service.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + capacity;
        result = prime * result
                + ((conference == null) ? 0 : conference.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Room other = (Room) obj;
        if (capacity != other.capacity)
            return false;
        if (conference == null) {
            if (other.conference != null)
                return false;
        } else if (!conference.equals(other.conference))
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", name=" + name + ", capacity=" + capacity
                + ", conference=" + conference + "]";
    }


}
