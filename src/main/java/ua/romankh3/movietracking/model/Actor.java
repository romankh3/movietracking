package ua.romankh3.movietracking.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "actor_id")
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message = "*Please provide your name")
    private String fullName;

    @Column(name = "active")
    private int active;

    @Column(name = "thdbId")
    private Integer thdbId;

    @Column(name = "picture_path")
    private String picturePath;

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Integer getThdbId() {
        return thdbId;
    }

    public void setThdbId(Integer thdbId) {
        this.thdbId = thdbId;
    }
}
