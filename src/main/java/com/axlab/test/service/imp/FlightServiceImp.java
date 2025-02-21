package com.axlab.test.service.imp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.axlab.test.exception.FlightNotFoundException;
import com.axlab.test.model.Flight;
import com.axlab.test.repository.FlightRepository;
import com.axlab.test.service.FlightService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightServiceImp implements FlightService {

	private final FlightRepository flightRepo;

	@Override
	public Flight getById(Long id) {
		return flightRepo.findById(id).orElseThrow(() -> new FlightNotFoundException(id));
	}

	@Override
	public Flight add(Flight flight) {
		return flightRepo.save(flight);
	}

	@Override
	public Iterable<Flight> getAll() {
		return flightRepo.findAll();
	}

	@Override
	public Page<Flight> retrieveUsingPagination(Pageable pageable) {
		return flightRepo.findAll(pageable);
	}

	@Override
	public String delete(Long id) {
		Flight flight = getById(id);

		try {
			flightRepo.delete(flight);
			return "success";
		} catch (Exception e) {
			throw new RuntimeException("Flight not deleted");
		}

	}

}
