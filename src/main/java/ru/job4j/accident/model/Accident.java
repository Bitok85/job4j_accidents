package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table
public class Accident {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;
    @ManyToOne
    @JoinColumn(name = "accident_type_id")
    private AccidentType type;

    @ManyToMany
    @JoinTable(
            name = "accidents_rules",
            joinColumns = { @JoinColumn(name = "accident_id")},
            inverseJoinColumns = { @JoinColumn(name = "rule_id")}
    )
    private Set<Rule> rules;

    public Accident(int id, String name, String text, String address) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
    }
}
