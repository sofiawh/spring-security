package com.simplon.labxpert.service.impl;

import com.simplon.labxpert.mapper.PatientMapper;
import com.simplon.labxpert.model.dto.PatientDTO;
import com.simplon.labxpert.model.entity.Patient;
import com.simplon.labxpert.model.enums.Gender;
import com.simplon.labxpert.repository.PatientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@SpringBootTest
@AutoConfigureMockMvc
class PatientServiceImplTest {

//    @Mock
//    private PatientRepository patientRepository;
//
//    @Mock
//    private PatientMapper patientMapper;
//
//    @InjectMocks
//    private PatientServiceImpl patientService;
//    private PatientDTO newPatient;
//    private PatientDTO updatedPatient;
//    private PatientDTO existingPatientDTO;
//    /**
//     * Setup method to initialize common objects before each test.
//     */
//    @BeforeEach
//    void setUp() {
//        newPatient = new PatientDTO();
//        newPatient.setPatientID(1L);
//        newPatient.setFirstName("patientFirstName");
//        newPatient.setLastName("patientLastName");
//        newPatient.setPatientEmail("test@example.com");
//        newPatient.setDateOfBirth(LocalDate.of(1990, 1, 1));
//        newPatient.setGender(Gender.MALE);
//        newPatient.setAddress("123 Street, City");
//        newPatient.setPhoneNumber("1234567890");
//
//        updatedPatient = new PatientDTO();
//        updatedPatient.setFirstName("UpdatedFirstName");
//        updatedPatient.setLastName("UpdatedLastName");
//        updatedPatient.setPatientEmail("updated@example.com");
//        updatedPatient.setDateOfBirth(LocalDate.of(1995, 5, 5));
//        updatedPatient.setGender(Gender.FEMALE);
//        updatedPatient.setAddress("456 Updated Street, City");
//        updatedPatient.setPhoneNumber("9876543210");
//
//        existingPatientDTO = new PatientDTO();
//        existingPatientDTO.setPatientID(1L);
//        existingPatientDTO.setFirstName("existingFirstName");
//        existingPatientDTO.setLastName("existingLastName");
//        existingPatientDTO.setPatientEmail("existing@example.com");
//        existingPatientDTO.setDateOfBirth(LocalDate.of(1990, 1, 1));
//        existingPatientDTO.setGender(Gender.MALE);
//        existingPatientDTO.setAddress("789 Existing Street, City");
//        existingPatientDTO.setPhoneNumber("11122334455");
//    }
//
//    /**
//     * Test case for retrieving all patients.
//     *
//     */
//    @Test
//    void getAllPatients()throws Exception {
//        when(patientRepository.findAll()).thenReturn(Arrays.asList(new Patient(), new Patient()));
//        when(patientMapper.toDTO(any(Patient.class))).thenReturn(newPatient);
//        List<PatientDTO> patientList = patientService.getAllPatients();
//        verify(patientRepository, times(1)).findAll();
//        verify(patientMapper, times(2)).toDTO(any(Patient.class));
//        assertEquals(2, patientList.size());
//        assertEquals(newPatient, patientList.get(0));
//    }
//
//    @Test
//    void getPatientById() throws Exception{
//        when(patientRepository.findById(2L)).thenReturn(Optional.empty());
//        assertThrows(NoSuchElementException.class, () -> patientService.getPatientById(2L));
//
//    }
//
//    @Test
//    void createPatient() throws Exception {
//        when(patientRepository.findByPatientEmail("test@example.com")).thenReturn(Optional.empty());
//        when(patientRepository.save(any(Patient.class))).thenAnswer(invocation -> {
//            Patient savedPatient = invocation.getArgument(0);
//            return savedPatient;
//        });
//        when(patientMapper.toDTO(any(Patient.class))).thenAnswer(invocation -> {
//            Patient patientToMap = invocation.getArgument(0);
//            return patientMapper.toDTO(patientToMap);
//    });
//        PatientDTO result = patientService.createPatient(newPatient);
//        assertNotNull(result);
//        assertEquals(existingPatientDTO, result);
//        verify(patientRepository, times(1)).findByPatientEmail("test@example.com");
//        verify(patientRepository, times(1)).save(any(Patient.class));
//        verify(patientMapper, times(1)).toDTO(any(Patient.class));
//    }
//
//    @Test
//    void updatePatient() throws Exception{
////        long patientId = existingPatientDTO.getPatientID();
////        when(patientRepository.findById(patientId)).thenReturn(Optional.of(patientMapper.toEntity(existingPatientDTO)));
////        when(patientRepository.findByPatientEmail(updatedPatient.getPatientEmail())).thenReturn(Optional.empty());
////        when(patientRepository.save(any(Patient.class))).thenReturn(patientMapper.toEntity(updatedPatient));
////        when(patientMapper.toDTO(any(Patient.class))).thenReturn(updatedPatient);
////        PatientDTO result = patientService.updatePatient(patientId, updatedPatient);
////        assertNotNull(result);
////        assertEquals(updatedPatient, result);
////        verify(patientRepository, times(1)).findById(patientId);
////        verify(patientRepository, times(1)).findByPatientEmail(updatedPatient.getPatientEmail());
////        verify(patientRepository, times(1)).save(any(Patient.class));
////        verify(patientMapper, times(1)).toDTO(any(Patient.class));
//    }
//
//    @Test
//    void deletePatient() throws Exception{
//        long patientId = existingPatientDTO.getPatientID();
//        System.out.println("patient id = "+patientId);
//        Patient existingPatientEntity = patientMapper.toEntity(existingPatientDTO);
////        when(patientRepository.findById(patientId)).thenReturn(existingPatientEntity);
//        patientService.deletePatient(patientId);
//        verify(patientRepository, times(1)).findById(patientId);
//        verify(patientRepository, times(1)).deleteById(patientId);
//
//
//
//    }


}






