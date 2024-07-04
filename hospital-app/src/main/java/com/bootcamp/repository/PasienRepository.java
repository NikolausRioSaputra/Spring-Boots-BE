package com.bootcamp.repository;

import com.bootcamp.model.Pasien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienRepository extends JpaRepository<Pasien, Long> {
}
