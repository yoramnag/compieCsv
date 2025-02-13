package com.example.compieCsv.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Schema(
        name = "Players",
        description = "Schema to hold Players INFO"
)
public class Players {

    @Id
    @Column(name="Id")
    @JsonProperty
    private int id;

    @Column(name="nickname")
    @NotEmpty(message = "nickname can NOT be null or empty")
    @JsonProperty
    private String nickname;

    @Column(name="first_name")
    @JsonProperty
    private String first_name;

    @Column(name="last_name")
    @JsonProperty
    private String last_name;

    @Column(name="position")
    @JsonProperty
    private String position;

    @Column(name="height")
    @JsonProperty
    private String height;

    @Column(name="weight")
    @JsonProperty
    private String weight;

    @Column(name="jersey_number")
    @JsonProperty
    private String jersey_number;

    @Column(name="college")
    @JsonProperty
    private String college;

    @Column(name="country")
    @JsonProperty
    private String country;

    @Column(name="draft_year")
    @JsonProperty
    private String draft_year;

    @Column(name="draft_round")
    @JsonProperty
    private String draft_round;

    @Column(name="draft_number")
    @JsonProperty
    private String draft_number;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="team_id")
    private Team team;

    public Players() {
    }

    public Players(int id, String nickname, String first_name, String last_name, String position, String height, String weight, String jersey_number, String college, String country, String draft_year, String draft_round, String draft_number, Team team) {
        this.id = id;
        this.nickname = nickname;
        this.first_name = first_name;
        this.last_name = last_name;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.jersey_number = jersey_number;
        this.college = college;
        this.country = country;
        this.draft_year = draft_year;
        this.draft_round = draft_round;
        this.draft_number = draft_number;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getJersey_number() {
        return jersey_number;
    }

    public void setJersey_number(String jersey_number) {
        this.jersey_number = jersey_number;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDraft_year() {
        return draft_year;
    }

    public void setDraft_year(String draft_year) {
        this.draft_year = draft_year;
    }

    public String getDraft_round() {
        return draft_round;
    }

    public void setDraft_round(String draft_round) {
        this.draft_round = draft_round;
    }

    public String getDraft_number() {
        return draft_number;
    }

    public void setDraft_number(String draft_number) {
        this.draft_number = draft_number;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Players{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", position='" + position + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", jersey_number='" + jersey_number + '\'' +
                ", college='" + college + '\'' +
                ", country='" + country + '\'' +
                ", draft_year='" + draft_year + '\'' +
                ", draft_round='" + draft_round + '\'' +
                ", draft_number='" + draft_number + '\'' +
                ", team=" + team +
                '}';
    }
}
