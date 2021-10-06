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

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
