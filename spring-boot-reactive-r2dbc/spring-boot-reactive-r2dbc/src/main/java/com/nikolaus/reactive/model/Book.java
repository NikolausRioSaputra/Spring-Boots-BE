package com.nikolaus.reactive.model;

import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@Builder
public class Book {

    @Id
    private int id;

    private String title;

    private String description;

    private boolean published;

    public Book(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

    public Book(int id, String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }
}

