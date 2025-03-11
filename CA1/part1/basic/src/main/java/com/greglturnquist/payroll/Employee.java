/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Entity // <1>
public class Employee {

	private @Id @GeneratedValue Long id; // <2>
	private String firstName;
	private String lastName;
	private String description;
	private int jobYears;


	protected Employee() {
	}

	public Employee(String firstName, String lastName, String description, int jobYears) {

		if(isFirstNameInvalid(firstName)){
			throw new IllegalArgumentException("First name cannot be empty!");
		}

		if(isLastNameInvalid(lastName)){
			throw new IllegalArgumentException("Last name cannot be empty!");
		}

		if(isDescriptionInvalid(description)){
			throw new IllegalArgumentException("Description cannot be empty!");
		}

		if (areJobYearsInvalid(jobYears)){
			throw new IllegalArgumentException("Insert a valid number of job years.");
		}

		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.jobYears = jobYears;

	}

	private boolean isFirstNameInvalid (String firstName) {
        return firstName == null || firstName.isBlank();
    }

	private boolean isLastNameInvalid (String lastName) {
		return lastName == null || lastName.isBlank();
	}

	private boolean isDescriptionInvalid (String description) {
		return description == null || description.isBlank();
	}

	private boolean areJobYearsInvalid(int jobYears) {

		return jobYears < 0 || jobYears > 100;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) &&
			Objects.equals(firstName, employee.firstName) &&
			Objects.equals(lastName, employee.lastName) &&
			Objects.equals(description, employee.description) &&
			Objects.equals(jobYears, employee.jobYears);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, firstName, lastName, description, jobYears);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName)  {
		if(isFirstNameInvalid(firstName))
			throw new IllegalArgumentException("First name cannot be empty!");

		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(isLastNameInvalid(lastName))
			throw new IllegalArgumentException("Last name cannot be empty!");
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(isDescriptionInvalid(description))
			throw new IllegalArgumentException("Description cannot be empty!");

		this.description = description;
	}

	public int getJobYears(){
		return jobYears;
	}

	public void setJobYears(int jobYears) {
		if(areJobYearsInvalid(jobYears))
			throw new IllegalArgumentException("Insert a valid number of job years.");
		this.jobYears = jobYears;
	}

	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", description='" + description + '\'' +
			", jobYears='" + jobYears +
			'}';
	}
}
// end::code[]
