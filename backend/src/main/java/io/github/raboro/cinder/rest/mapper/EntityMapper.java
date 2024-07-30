package io.github.raboro.cinder.rest.mapper;

/**
 * Maps between Entity and DTO
 *
 * @param <E> Type of Entity
 * @param <D> Type of DTO
 */
interface EntityMapper<E, D> {

    D toDTO(E e);

    E toEntity(D d);
}
