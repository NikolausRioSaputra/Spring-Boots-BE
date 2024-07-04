package com.bootcamp.repository;

import com.bootcamp.model.Dokter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DokterRepository extends JpaRepository<Dokter, Long> {
}
