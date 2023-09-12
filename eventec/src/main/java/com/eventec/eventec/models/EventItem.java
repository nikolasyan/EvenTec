package com.eventec.eventec.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "event_items")
public class EventItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String img;
    private String description;

    private String category;

    private String dateEvent;

    private String hourStart;

    private String hourEnd;

    @Override
    public String toString(){
        return String.format("EventItem{id=%d, title=%'s', img=%'s', description='%s', category='%s', dateEvent='%s', hourStart='%s', hourEnd=%'s'}",
                id, title, img,description, category, dateEvent, hourStart, hourEnd);
    }
}
