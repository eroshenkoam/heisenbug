package io.github.eroshenkoam.heisenbug.service.dto;

import io.github.eroshenkoam.heisenbug.domain.User;

/**
 * A DTO representing a todo, with only the public attributes.
 */
public class TodoDTO {

    private Long id;

    private String title;

    public Long getId() {
        return id;
    }

    public TodoDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TodoDTO setTitle(String title) {
        this.title = title;
        return this;
    }

}
