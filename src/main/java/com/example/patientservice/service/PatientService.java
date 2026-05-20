package com.example.patientservice.service;

import com.example.patientservice.dto.PatientRequestDTO;
import com.example.patientservice.dto.PatientResponseDTO;
import com.example.patientservice.exception.EmailAlreadyExistsException;
import com.example.patientservice.exception.PatientNotFoundException;
import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;
import com.example.patientservice.mapper.PatientMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }
    public List<PatientResponseDTO> getAllPatients(){
       return patientRepository.findAll().stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.email())){
            throw new EmailAlreadyExistsException("The entered email already exists" + patientRequestDTO.email());
        }

        return PatientMapper.toDTO(patientRepository.save(PatientMapper.toModel(patientRequestDTO)));
    }

    public PatientResponseDTO updatePatient(UUID id,PatientRequestDTO patientRequestDTO)
    {
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Couldn't find the patient with ID: "+ id));
        if(patientRepository.existsByEmail(patientRequestDTO.email())){
            throw new EmailAlreadyExistsException("The entered email already exists" + patientRequestDTO.email());
        }

        patient.setName(patientRequestDTO.name());
        patient.setEmail(patientRequestDTO.email());
        patient.setAddress(patient.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.dateOfBirth()));

        return PatientMapper.toDTO(patientRepository.save(patient));
    }

}
