package com.example.compieCsv.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {

    @Id
    @Column(name="Id")
    @JsonProperty
    private int id;

    @Column(name="conference")
    @JsonProperty
    private String conference;

    @Column(name="division")
    @JsonProperty
    private String division;

    @Column(name="city")
    @JsonProperty
    private String city;

    @Column(name="name")
    @JsonProperty
    private String name;

    @Column(name="full_name")
    @JsonProperty
    private String full_name;

    @Column(name="abbreviation")
    @JsonProperty
    private String abbreviation;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy="team",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Players> playersList;

    public Team() {
    }

    public Team(int id, String conference, String division, String city, String name, String full_name, String abbreviation, List<Players> playersList) {
        this.id = id;
        this.conference = conference;
        this.division = division;
        this.city = city;
        this.name = name;
        this.full_name = full_name;
        this.abbreviation = abbreviation;
        this.playersList = playersList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<Players> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Players> playersList) {
        this.playersList = playersList;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", conference='" + conference + '\'' +
                ", division='" + division + '\'' +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", playersList=" + playersList +
                '}';
    }
}
