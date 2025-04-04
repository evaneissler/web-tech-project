package edu.tcu.cs.backend.gameSchedule;

import edu.tcu.cs.backend.user.User;
import edu.tcu.cs.backend.user.UserRepository;
import edu.tcu.cs.backend.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameScheduleServiceTest {

    @Mock
    GameScheduleRepository gameScheduleRepository;

    @InjectMocks
    GameScheduleService gameScheduleService;

    List<GameSchedule> gameSchedules;

    @BeforeEach
    void setUp() {
        GameSchedule schedule1 = new GameSchedule();
        schedule1.setName("Football Schedule");

        GameSchedule schedule2 = new GameSchedule();
        schedule2.setName("Basketball Schedule");

        GameSchedule schedule3 = new GameSchedule();
        schedule3.setName("Baseball Schedule");

        this.gameSchedules = new ArrayList<>();
        this.gameSchedules.add(schedule1);
        this.gameSchedules.add(schedule2);
        this.gameSchedules.add(schedule3);
    }

    @Test
    void testFindAllSuccess() {
        given(this.gameScheduleRepository.findAll()).willReturn(this.gameSchedules);

        List<GameSchedule> schedules = this.gameScheduleService.findAll();

        assertThat(schedules.size()).isEqualTo(this.gameSchedules.size());

        verify(this.gameScheduleRepository, times(1)).findAll();
    }

    @Test
    void testAddGameScheduleSuccess() {
        GameSchedule newSchedule = new GameSchedule();
        newSchedule.setName("Swimming Schedule");

        given(this.gameScheduleRepository.save(newSchedule)).willReturn(newSchedule);

        GameSchedule returnedSchedule = this.gameScheduleService.save(newSchedule);

        assertThat(returnedSchedule.getName()).isEqualTo(newSchedule.getName());
        verify(this.gameScheduleRepository, times(1)).save(newSchedule);
    }

    @Test
    void testFindGameScheduleByIdSuccess() {
        GameSchedule schedule = new GameSchedule();
        schedule.setName("Wrestling Schedule");

        given(this.gameScheduleRepository.findById(1)).willReturn(Optional.of(schedule));

        GameSchedule returnedSchedule = this.gameScheduleService.findById(1);

        assertThat(returnedSchedule.getId()).isEqualTo(schedule.getId());
        assertThat(returnedSchedule.getName()).isEqualTo(schedule.getName());
        verify(this.gameScheduleRepository, times(1)).findById(1);
    }
}
