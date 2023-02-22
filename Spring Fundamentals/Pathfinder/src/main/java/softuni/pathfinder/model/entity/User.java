package softuni.pathfinder.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import softuni.pathfinder.model.enums.Level;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    @Size(min = 2)
    private String username;

    @Column(nullable = false)
    @Size(min = 2)
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private Integer age;

    //o	Each registered user should have a "User" role
    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "roles",
//            joinColumns = @JoinColumn(name = "user_entity_id"),
//            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    private Level level;

    public User() {
        this.roles = new HashSet<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
