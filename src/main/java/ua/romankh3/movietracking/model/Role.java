package ua.romankh3.movietracking.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    public final static String ADMIN = "ADMIN";
    public final static String USER = "USER";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    @Column(name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
