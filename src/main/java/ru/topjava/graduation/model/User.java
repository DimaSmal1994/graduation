package ru.topjava.graduation.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ru.topjava.graduation.HasId;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(
        name = "users_unique_email_idx", columnNames = "email")})
public class User implements HasId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "emal", nullable = false, unique = true)
    @Email
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserVote> votes;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 100)
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

    @Override
    public Integer getId() {
        return id;
    }

    @Override
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", votes=" + votes +
                ", roles=" + roles +
                '}';
    }
}
