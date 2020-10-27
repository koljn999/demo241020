package com.example.demo.model.auto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "listGym")
    private List<Owner> owners;
}
