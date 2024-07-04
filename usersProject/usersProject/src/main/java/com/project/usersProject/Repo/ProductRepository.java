package com.project.usersProject.Repo;

import com.project.usersProject.Entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPrice(double price, Pageable pageable);
}
