package com.simplon.labxpert.model.entity;

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
    @OneToMany(mappedBy = "patient")
    private List<Sample> samples;

}
