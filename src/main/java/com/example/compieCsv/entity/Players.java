package com.example.compieCsv.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Schema(
        name = "Players",
        description = "Schema to hold Players INFO"
)
public class Players {

    @Id
    @Column(name="Id")
    @Schema(
            description = "Player ID",
            example = "1"
    )
    private int id;

    @Column(name="nickname")
    @NotEmpty(message = "nickname can NOT be null or empty")
    @Schema(
            description = "player nickname",
            example = "Ikeee"
    )
    private String nickname;

    private String first_name;

    public Players() {
    }

    public Players(int id, String nickname,String first_name) {
        this.id = id;
        this.nickname = nickname;
        this.first_name = first_name;
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

    @Override
    public String toString() {
        return "Players{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
