package softuni.pathfinder.model.entity;

import jakarta.persistence.*;
import softuni.pathfinder.model.enums.RoleName;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName role;

    public Role() {
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }
}
