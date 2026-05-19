package com.example.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PatientRequestDTO(
        @NotBlank
        @Size(
                max=100,
                message ="Name cannot exceed 100 characters"
        )
        String name,
        @NotBlank
        @Email(
                message="Email should be valid"
        )
        String email,
        @NotBlank
                (
                        message = "Address is required"
                )
        String address,
        @NotBlank
                (
                        message = "DOB is required"
                )
        String dateOfBirth,
        @NotBlank(
                message = "registeredDate cannot be left blank"
        )
        String registeredDate
) {
}
