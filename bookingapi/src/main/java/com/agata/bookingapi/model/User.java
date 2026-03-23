package com.agata.bookingapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private int chances = 3;
    private boolean blocked = false;

    public User() {
    }

    public User(long id, String name, String email, String password, int chances, boolean blocked) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.chances = chances;
        this.blocked = blocked;
    }

    public User(String name, String email, String password, int chances, boolean blocked) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.chances = chances;
        this.blocked = blocked;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getChances() {
        return chances;
    }

    public void setChances(int chances) {
        this.chances = chances;
    }
}