package com.bootcamp.repository;

import com.bootcamp.model.Penanganan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PenangananRepository extends JpaRepository<Penanganan, Long> {
    List<Penanganan> findByPasien_NamaPasien(String namaPasien);
}
