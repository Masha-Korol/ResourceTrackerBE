package com.example.resource_tracker.data.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_authorization_roles", schema = "public")
public class AuthorizationRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private ERoleType roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public ERoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(ERoleType roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizationRole role = (AuthorizationRole) o;
        return roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }

    @Override
    public String toString() {
        return "AuthorizationRole{" +
                "roleId=" + roleId +
                ", roleName=" + roleName +
                '}';
    }
}
