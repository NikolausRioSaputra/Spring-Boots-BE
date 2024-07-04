package com.bootcamp.repository;

import com.bootcamp.model.Penyakit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenyakitRepository extends JpaRepository<Penyakit, Long> {
}