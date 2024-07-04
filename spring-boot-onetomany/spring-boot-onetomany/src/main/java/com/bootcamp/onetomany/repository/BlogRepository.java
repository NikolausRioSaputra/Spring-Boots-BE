package com.bootcamp.onetomany.repository;

import com.bootcamp.onetomany.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
