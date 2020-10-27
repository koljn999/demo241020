package com.example.demo.model.auto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Address address;

    @OneToMany(mappedBy="owner")
    private List<Car> cars;

    @ManyToMany()
    @JoinTable(name="gym_owner", joinColumns = @JoinColumn(name = "gym_id"),
    inverseJoinColumns = @JoinColumn(name="owner_id"))
    private List<Gym> listGym;

}
