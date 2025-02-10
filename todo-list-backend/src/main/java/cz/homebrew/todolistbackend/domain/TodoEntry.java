package cz.homebrew.todolistbackend.domain;

import cz.homebrew.todolistbackend.domain.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TodoEntry extends BaseEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private boolean finished = false;
}
