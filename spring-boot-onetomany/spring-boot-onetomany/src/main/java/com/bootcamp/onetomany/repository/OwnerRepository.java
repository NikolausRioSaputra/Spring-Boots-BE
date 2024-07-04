package com.bootcamp.onetomany.repository;

import com.bootcamp.onetomany.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
