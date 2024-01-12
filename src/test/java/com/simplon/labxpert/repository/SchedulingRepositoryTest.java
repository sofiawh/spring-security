package com.simplon.labxpert.repository;

import com.simplon.labxpert.LabXpertApplication;
import com.simplon.labxpert.model.entity.Analysis;
import com.simplon.labxpert.model.entity.Scheduling;
import com.simplon.labxpert.model.entity.User;
import com.simplon.labxpert.model.enums.AnalysisStatus;
import com.simplon.labxpert.model.enums.Priority;
import com.simplon.labxpert.model.enums.UserRole;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@SpringJUnitConfig(LabXpertApplication.class)
class SchedulingRepositoryTest {
    private SchedulingRepository schedulingRepository;
    private UserRepository userRepository;
    private AnalysisRepository analysisRepository;
    private Scheduling scheduling1;
    private Scheduling scheduling2;
    private User user;
    private Analysis analysis;

    @Autowired
    public SchedulingRepositoryTest(SchedulingRepository schedulingRepository, UserRepository userRepository, AnalysisRepository analysisRepository) {
        this.schedulingRepository = schedulingRepository;
        this.userRepository = userRepository;
        this.analysisRepository = analysisRepository;
    }

    @BeforeEach
    void setUp() {
        // Create a user
        user = user.builder()
                .username("John")
                .password("123456")
                .emailVerified(true)
                .email("John@gmail.com")
                .userRole(UserRole.ADMINISTRATOR)
                .personalInfo("John Doe")
                .build();
        userRepository.save(user);
        // Create an analysis
        analysis = analysis.builder()
                .startDate(LocalDate.of(2001, 01, 01))
                .endDate(LocalDate.of(2001, 01, 02))
                .analysisStatus(AnalysisStatus.NEED_SCHEDULING)
                .comments("Analysis of the sample")
                .user(user)
                .build();
        analysisRepository.save(analysis);
        // Create a scheduling
        scheduling1 = scheduling1.builder()
                .startDateAndTime(LocalDate.of(2001, 01, 01))
                .endDateAndTime(LocalDate.of(2001, 01, 02))
                .priority(Priority.NORMAL)
                .notes("Scheduling of the analysis")
                .user(user)
                .analysis(analysis)
                .build();
        schedulingRepository.save(scheduling1);
        // Create a scheduling
        scheduling2 = scheduling2.builder()
                .startDateAndTime(LocalDate.of(2005, 01, 01))
                .endDateAndTime(LocalDate.of(2005, 01, 02))
                .priority(Priority.URGENT)
                .notes("URGENT Scheduling of the analysis")
                .user(user)
                .analysis(analysis)
                .build();
        schedulingRepository.save(scheduling2);
    }

    @Test
    @Order(1)
    @DisplayName("Test Scheduling Repository FindById Method")
    public void schedulingRepository_finById_ReturnsScheduling() {
        //Arrange
        //Act
        Optional<Scheduling> schedulingOptional = schedulingRepository.findById(scheduling2.getSchedulingID());
        //Assert
        assertTrue(schedulingOptional.isPresent());
        assertEquals(scheduling2.getSchedulingID(), schedulingOptional.get().getSchedulingID());
    }

    @Test
    @Order(2)
    @DisplayName("Test Scheduling Repository FindAll Method")
    public void schedulingRepository_findAll_ReturnsSchedulingList() {
        //Arrange
        Scheduling scheduling3 = Scheduling.builder()
                .startDateAndTime(LocalDate.of(2005, 01, 01))
                .endDateAndTime(LocalDate.of(2005, 01, 02))
                .priority(Priority.URGENT)
                .notes("URGENT Scheduling of the analysis")
                .user(user)
                .analysis(analysis)
                .build();
        schedulingRepository.save(scheduling3);
        //Act
        List<Scheduling> schedulingList = schedulingRepository.findAll();
        //Assert
        assertEquals(3, schedulingList.size());
        assertFalse(schedulingList.isEmpty());
        assertEquals(scheduling1.getSchedulingID(), schedulingList.get(0).getSchedulingID());
    }

    @Test
    @Order(3)
    @DisplayName("Test Scheduling Repository Save Method")
    public void schedulingRepository_save_ReturnsScheduling() {
        //Arrange
        Scheduling scheduling3 = Scheduling.builder()
                .startDateAndTime(LocalDate.of(2005, 01, 01))
                .endDateAndTime(LocalDate.of(2005, 01, 02))
                .priority(Priority.URGENT)
                .notes("URGENT Scheduling of the analysis")
                .user(user)
                .analysis(analysis)
                .build();
        //Act
        Scheduling schedulingSaved = schedulingRepository.save(scheduling3);
        //Assert
        assertEquals(scheduling3.getSchedulingID(), schedulingSaved.getSchedulingID());
    }

    @Test
    @Order(4)
    @DisplayName("Test Scheduling Repository DeleteById Method")
    public void schedulingRepository_deleteById_ReturnsVoid() {
        //Arrange
        //Act
        schedulingRepository.deleteById(scheduling2.getSchedulingID());
        Optional<Scheduling> schedulingOptional = schedulingRepository.findById(scheduling2.getSchedulingID());
        //Assert
        assertFalse(schedulingRepository.findById(scheduling2.getSchedulingID()).isPresent());
        assertFalse(schedulingOptional.isPresent());
    }

    @Test
    @Order(5)
    @DisplayName("Test Scheduling Repository Update Method")
    public void schedulingRepository_update_ReturnsScheduling() {
        //Arrange
        Scheduling scheduling3 = Scheduling.builder()
                .startDateAndTime(LocalDate.of(2005, 01, 01))
                .endDateAndTime(LocalDate.of(2005, 01, 02))
                .priority(Priority.URGENT)
                .notes("URGENT Scheduling of the analysis")
                .user(user)
                .analysis(analysis)
                .build();
        schedulingRepository.save(scheduling3);
        //Act
        scheduling3.setNotes("URGENT Scheduling of the analysis UPDATED");
        Scheduling schedulingUpdated = schedulingRepository.save(scheduling3);
        //Assert
        assertEquals(scheduling3.getSchedulingID(), schedulingUpdated.getSchedulingID());
        assertEquals("URGENT Scheduling of the analysis UPDATED", schedulingUpdated.getNotes());
    }


    @AfterEach
    void tearDown() {
        schedulingRepository.deleteAll();
        analysisRepository.deleteAll();
        userRepository.deleteAll();
    }
}