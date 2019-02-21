package com.kwetter.domain;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Trend {
    @Id
    @Column( columnDefinition = "BINARY(16)", length = 16 )
    private UUID id;

    @Column(unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @OrderBy(value = "dateUpdated DESC")
    private List<Kweet> Kweets;

    protected  Trend() {

    }

    public Trend(UUID id, String name, List<Kweet> Kweets) {
        this.id = id;
        this.name = name;
        this.Kweets = Kweets;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Kweet> getKweets() {
        return Kweets;
    }
}
