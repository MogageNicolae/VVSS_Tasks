package tasks.services;

import org.junit.jupiter.api.*;
import tasks.model.ArrayTaskList;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {

    private DateService dateService;
    private static TasksService tasksService;
    private ArrayTaskList tasks;


    @BeforeAll
    static void setUpAll() {
        System.out.println("Initializing Tests...");
        var tasks = new ArrayTaskList();
        tasksService = new TasksService(tasks);
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Tests Finished.");
        tasksService = null;

    }

    @BeforeEach
    void setUp() {
        dateService = new DateService(tasksService);
    }

    @AfterEach
    void tearDown() {
        dateService = null;
    }

    @Test
    void test1() {
        String time = "1:30";
        Date noTimeDate = new Date(2006, 7, 12);

        Date result = dateService.getDateMergedWithTime(time, noTimeDate);

        assertEquals(result, new Date(2006, 7, 12, 1, 30));
    }

    @Test
    void test2() {
        String time = "03:59";
        Date noTimeDate = new Date(2006, 7, 12);

        Date result = dateService.getDateMergedWithTime(time, noTimeDate);

        assertEquals(result, new Date(2006, 7, 12, 3, 59));
    }

    @Test
    void test3() {
        String time = "24:30";
        Date noTimeDate = new Date(2006, 7, 12);

        try {
            dateService.getDateMergedWithTime(time, noTimeDate);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test
    void test4() {
        String time = "03:61";
        Date noTimeDate = new Date(2006, 7, 12);

        try {
            dateService.getDateMergedWithTime(time, noTimeDate);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test
    void test5() {
        String time = "scoala de soferi";
        Date noTimeDate = new Date(2007, 12, 11);

        try {
            dateService.getDateMergedWithTime(time, noTimeDate);
            assert false;
        } catch (NumberFormatException e) {
            assert true;
        }
    }
}
