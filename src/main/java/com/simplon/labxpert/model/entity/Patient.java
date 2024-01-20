package com.simplon.labxpert.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simplon.labxpert.model.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_id_sequence",
            sequenceName = "patient_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_id_sequence"
    )
    private long patientID;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "patientEmail", unique = true)
    private String patientEmail;
    @Column(name = "lastName")
    private String lastName;
    @Column(name="dateOfBirth")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "address")
    private String address;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    // TODO : TO @Ayoub ait si ahmad CORRECT THAT SHOULD RETURN THE LIST OF SAMPLES DEPENDING ON THE CONTEXT
    // TODO : TO @Ayoub ait si ahmad CORRECT ALSO THE FETCH TYPE AND THE CASCADE TYPE AND THE DELETE TYPE
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Sample> samples;
    //TODO :TO @chaimaa mahy ADD THE HISTORY OF THE ANALYSIS
}
