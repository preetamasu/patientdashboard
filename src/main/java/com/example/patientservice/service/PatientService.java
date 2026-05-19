package com.example.patientservice.service;

import com.example.patientservice.dto.PatientResponseDTO;
import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;
import com.example.patientservice.mapper.PatientMapper;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    public List<PatientResponseDTO> getAllPatients(){
       return patientRepository.findAll().stream().map(PatientMapper::toDTO).toList();
    }

}
