package com.axlab.test.exception;

public class FlightNotFoundException extends RuntimeException {

	public FlightNotFoundException(Long id) {
		super("Flight with ID:" + id + " not found");
	}
}