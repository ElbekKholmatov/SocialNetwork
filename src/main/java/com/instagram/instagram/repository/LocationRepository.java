package com.instagram.instagram.repository;

import com.instagram.instagram.domains.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}