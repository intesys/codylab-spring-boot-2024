package it.intesys.academy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "YUSER")
public class User {

    private @Id long id;

    private @Column(nullable = false) String username;

    private @Column(nullable = false) String name;

    private @Column(nullable = false) String surname;

    private @Column(nullable = false) String email;

}
