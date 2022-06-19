package com.fsb.jwt_authentication.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
