package com.example.cursework.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String author;
    @Column(length = 1000)
    private String description;
    private int price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "book")
    private List<Image> imageList = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private User user;

    private Long previewImageId;
    private LocalDateTime dateOfCreate;
    @PrePersist
    private void init() {
        dateOfCreate = LocalDateTime.now();
    }

    public void addImageToBook(Image image) {
        image.setBook(this);
        imageList.add(image);
    }
}
