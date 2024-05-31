package org.backendspring_boot.backendspring_boot.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private int id;
    private String name;
    private String email;
    private int age;

    @ManyToOne
    private Antivirus antivirus;
}
