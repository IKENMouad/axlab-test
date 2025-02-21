package com.axlab.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axlab.test.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
