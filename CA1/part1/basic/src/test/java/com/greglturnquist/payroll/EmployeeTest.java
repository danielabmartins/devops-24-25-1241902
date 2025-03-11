package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class EmployeeTest {

    @Test
    void shouldCreateEmployee() {
        //Arrange

        // Act
        Employee employee = new Employee("Frodo", "Baggins", "Ring Bearer", 3);

        // Assert
        assertNotNull(employee);
        assertEquals("Frodo", employee.getFirstName());
        assertEquals("Baggins",employee.getLastName());
        assertEquals("Ring Bearer", employee.getDescription());
        assertEquals(3,employee.getJobYears());
    }


    public static Stream<Arguments> provideInvalidArguments() {
        return Stream.of(
                arguments(null,"Baggins","Ring Bearer",3,"First name cannot be empty!"),
                arguments("","Baggins","Ring Bearer",3,"First name cannot be empty!"),
                arguments(" ","Baggins","Ring Bearer",3,"First name cannot be empty!"),
                arguments("Frodo",null,"Ring Bearer",3,"Last name cannot be empty!"),
                arguments("Frodo","","Ring Bearer",3,"Last name cannot be empty!"),
                arguments("Frodo"," ","Ring Bearer",3,"Last name cannot be empty!"),
                arguments("Frodo","Baggins",null,3,"Description cannot be empty!"),
                arguments("Frodo","Baggins","",3,"Description cannot be empty!"),
                arguments("Frodo","Baggins"," ",3,"Description cannot be empty!"),
                arguments("Frodo","Baggins","Ring Bearer",-1,"Insert a valid number of job years."),
                arguments("Frodo","Baggins","Ring Bearer",101,"Insert a valid number of job years.")
                );
    }
    @ParameterizedTest
    @MethodSource("provideInvalidArguments")
    void testInvalidArguments(String firstName,String lastName, String description, int jobYears, String expectedMessage) throws IllegalArgumentException {

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName,lastName,description,jobYears);
        });
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testSetFirstName_ValidValue() {
        //arrange
        Employee employee = new Employee();
        //act
        employee.setFirstName("Bilbo");
        //assert
        assertEquals("Bilbo",employee.getFirstName());
    }

    @Test
    void testSetFirstName_InvalidValue() {
        Employee employee = new Employee();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setFirstName(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setFirstName("")),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setFirstName(" "))
        );
    }

    @Test
    void testSetLastName_ValidValue() {
        //arrange
        Employee employee = new Employee();
        //act
        employee.setLastName("Baggins");
        //assert
        assertEquals("Baggins",employee.getLastName());
    }

    @Test
    void testSetLastName_InvalidValue() {
        Employee employee = new Employee();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setLastName(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setLastName("")),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setLastName(" "))
        );
    }

    @Test
    void testSetDescription_ValidValue() {
        //arrange
        Employee employee = new Employee();
        //act
        employee.setDescription("Ring Bearer");
        //assert
        assertEquals("Ring Bearer",employee.getDescription());
    }

    @Test
    void testSetDescription_InvalidValue() {
        Employee employee = new Employee();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setDescription(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setDescription("")),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setDescription(" "))
        );
    }


    @Test
    void testSetJobYears_ValidValue() {
        //arrange
        Employee employee = new Employee();
        //act
        employee.setJobYears(5);
        //assert
        assertEquals(5, employee.getJobYears());
    }
    @Test
    void testSetJobYears_InvalidValue() {
        Employee employee = new Employee();

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setJobYears(-1)),
                () -> assertThrows(IllegalArgumentException.class, () -> employee.setJobYears(101))
        );
    }
}