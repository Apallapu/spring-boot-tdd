package com.poc.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;
	@Schema(description = "Unique identifier of the Customer.",
			example = "1", required = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Schema(description = "firstName of the customer.",
			example = "Raju", required = true)
	@NotBlank
	@Size(max = 100)
	@Column(name = "firstname")
	private String firstName;
	@Schema(description = "lastName of the customer.",
			example = "Pallapu", required = true)
	@NotBlank
	@Size(max = 100)
	@Column(name = "lastname")
	private String lastName;

	protected Customer() {
	}

	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
	}
}