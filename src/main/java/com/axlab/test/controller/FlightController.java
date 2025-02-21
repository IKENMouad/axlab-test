package com.axlab.test.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.axlab.test.model.Flight;
import com.axlab.test.service.FlightService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/flights")
@CrossOrigin("*")
public class FlightController {

	private final FlightService flightService;

	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping
	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public ResponseEntity<?> retrieveWithPagination(@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(defaultValue = "10", name = "size") int size,
			@RequestParam(defaultValue = "id", name = "column") String column,
			@RequestParam(defaultValue = "asc", name = "order") String order) {
		if (page != null) {
			Pageable pageable = PageRequest.of(page, size)
					.withSort(Sort.by("asc".equals(order) ? Sort.Direction.ASC : Sort.Direction.DESC, column));
			return ResponseEntity.ok(flightService.retrieveUsingPagination(pageable));
		}
		return ResponseEntity.ok(flightService.getAll());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
	public Flight retrieve(@PathVariable("id") Long id) {
		return flightService.getById(id);
	}

	@PostMapping
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Flight add(@RequestBody @Valid Flight flight, BindingResult bindingResult) {
		return flightService.add(flight);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	public String delete(@PathVariable("id") Long id) {
		return flightService.delete(id);
	}

}
