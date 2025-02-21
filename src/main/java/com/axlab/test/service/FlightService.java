package com.axlab.test.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.axlab.test.model.Flight;

public interface FlightService {

	Flight add(Flight flight);

	Flight getById(Long id);

	String delete(Long id);

	Iterable<Flight> getAll();

	Page<Flight> retrieveUsingPagination(Pageable pageable);
}
