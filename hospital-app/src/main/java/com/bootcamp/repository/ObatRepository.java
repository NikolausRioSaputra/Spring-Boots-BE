package com.bootcamp.repository;

import com.bootcamp.model.Obat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ObatRepository extends JpaRepository<Obat, Long> {
}
