package cz.homebrew.todolistbackend.repository.base;

import cz.homebrew.todolistbackend.domain.base.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity, ID extends Serializable>
        extends JpaRepository<T, ID> {

    /**
     * <p>
     *      Retrieves an entity by its id. When it does not find
     *      any corresponding instance then {@link EntityNotFoundException}
     *      is thrown.
     * </p>
     *
     * @param id Id which identifies {@linkplain T} instance. Must not be null.
     *
     * @return {@link T} instance.
     * @throws EntityNotFoundException When no corresponding
     * entity is found.
     */
    default T mustFindById(ID id) throws EntityNotFoundException {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException("Entity not found for ID: " + id));
    }

}
