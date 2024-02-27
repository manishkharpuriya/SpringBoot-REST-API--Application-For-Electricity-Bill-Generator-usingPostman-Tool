package com.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entity.Electricity;
public interface ElectricityRepo extends JpaRepository<Electricity, Integer> {

}
