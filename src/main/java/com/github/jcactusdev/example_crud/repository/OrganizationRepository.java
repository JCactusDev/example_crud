package com.github.jcactusdev.example_crud.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.jcactusdev.example_crud.entity.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {
    
    /**
	 * Returns whether an entity with the given id exists.
	 *
	 * @param name must not be {@literal null}.
	 * @return {@literal true} if an entity with the given name exists, {@literal false} otherwise.
	 * @throws IllegalArgumentException if {@literal name} is {@literal null}.
	 */
    boolean existsByName(String name);

}