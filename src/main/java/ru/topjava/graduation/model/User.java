package ru.topjava.graduation.model;

import java.util.List;
import java.util.Set;

public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private List<UserVote> votes;
    private Set<Role> roles;

    public User() {
    }

    public User(Integer id, String name, String email, String password, List<UserVote> votes, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.votes = votes;
        this.roles = roles;
    }

    public User(String name, String email, String password, List<UserVote> votes, Set<Role> roles) {
        this.id = null;
        this.name = name;
        this.email = email;
        this.password = password;
        this.votes = votes;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
