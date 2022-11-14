package com.ford.sparepartbooking.repository;

import com.ford.sparepartbooking.models.sparepart.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SparePartRepository extends JpaRepository<SparePart, Long> {
    SparePart findByName(String name);
}