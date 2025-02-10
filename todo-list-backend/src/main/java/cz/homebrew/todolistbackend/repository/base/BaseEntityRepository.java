package cz.homebrew.todolistbackend.repository.base;

import cz.homebrew.todolistbackend.domain.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
