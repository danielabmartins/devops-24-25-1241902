package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class EmployeeTest {

    public static Stream<Arguments> provideInvalidFirstName() {
        return Stream.of(
                arguments(null, "First name cannot be empty!"),
                arguments("", "First name cannot be empty!"),
                arguments(" ", "First name cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidFirstName")
    void testInvalidFirstNames(String firstName, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String lastName = "Baggins";
        String description = "Ring Bearer";
        int jobYears = 3;

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName,lastName,description,jobYears);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    public static Stream<Arguments> provideInvalidLastName() {
        return Stream.of(
                arguments(null, "Last name cannot be empty!"),
                arguments("", "Last name cannot be empty!"),
                arguments(" ", "Last name cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidLastName")
    void testInvalidLastNames(String lastName, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String firstName = "Frodo";
        String description = "Ring Bearer";
        int jobYears = 3;

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName,lastName,description,jobYears);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    public static Stream<Arguments> provideInvalidDescription() {
        return Stream.of(
                arguments(null, "Description cannot be empty!"),
                arguments("", "Description cannot be empty!"),
                arguments(" ", "Description cannot be empty!")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidDescription")
    void testInvalidDescription(String description, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        int jobYears = 3;

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName,lastName,description,jobYears);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    public static Stream<Arguments> provideInvalidJobYears() {
        return Stream.of(
                arguments(-1, "Insert a valid number of job years."),
                arguments(0, "Insert a valid number of job years."),
                arguments(101, "Insert a valid number of job years.")
        );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidJobYears")
    void testInvalidJobYears(int jobYears, String expectedMessage) throws IllegalArgumentException {
        // Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "Ring bearer";

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName,lastName,description,jobYears);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }







}