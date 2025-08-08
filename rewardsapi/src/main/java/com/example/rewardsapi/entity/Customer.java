package com.example.rewardsapi.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Customer {
    @Id
    private String id;
    private String name;

    public Customer() { this.id = UUID.randomUUID().toString(); }

    public Customer(String name) { this(); this.name = name; }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
