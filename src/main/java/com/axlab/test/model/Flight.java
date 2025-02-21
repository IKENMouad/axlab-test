package com.axlab.test.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 3, message = "invalid IATA carrier code, should be less than 3 letters")
	private String iataCarrierCode;

	@NotNull
	private String originAirportCode;

	@NotNull
	private String destinationAirportCode;

	@NotNull
	private Date departureDate;

	@NotNull
	@Column(length = 4)
	private String number;
}
