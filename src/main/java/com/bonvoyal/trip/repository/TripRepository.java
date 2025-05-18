package com.bonvoyal.trip.repository;

import com.bonvoyal.trip.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Trip} entities.
 *
 * <p>Extends {@link JpaRepository} to provide CRUD operations and query methods
 * for the {@link Trip} entity using Spring Data JPA.</p>
 *
 * <p>By extending JpaRepository, this interface inherits methods such as:
 * <ul>
 *   <li>{@code findAll()}</li>
 *   <li>{@code findById(Long id)}</li>
 *   <li>{@code save(Trip entity)}</li>
 *   <li>{@code deleteById(Long id)}</li>
 * </ul>
 * </p>
 *
 * <p>You can define additional custom query methods here using Spring Data JPA's
 * query derivation or JPQL annotations if needed.</p>
 *
 * @author tnowBV
 */
public interface TripRepository extends JpaRepository<Trip, Long> {
}
