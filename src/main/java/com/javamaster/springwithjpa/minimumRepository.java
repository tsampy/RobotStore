package com.javamaster.springwithjpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface minimumRepository extends JpaRepository<minimumInfo, Long> {
}