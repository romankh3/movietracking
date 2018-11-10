package ua.romankh3.movietracking.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
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

}
