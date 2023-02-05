package ua.prof.hw5.models;

import jakarta.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "genre_id")
    private int id;

    @Column(name = "genre_name")
    private String name;

    @Column(name = "genre_description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    Genre(){}
    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
