package com.example.patientservice.mapper;

import com.example.patientservice.dto.PatientResponseDTO;
import com.example.patientservice.model.Patient;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient){
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO(
                patient.getId().toString(),
                patient.getFirstname(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getDateOfBirth().toString()
        );
        return patientResponseDTO;
    }
}
