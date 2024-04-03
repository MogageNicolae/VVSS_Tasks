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
    void ecpTest1(){
        String time = "11:30";
        Date noTimeDate = new Date(2007,12,11);

        Date result = dateService.getDateMergedWithTime(time, noTimeDate);

        assertEquals(result, new Date(2007,12,11,11,30));
    }

    @Test
    void ecpTest2(){
        String time = "scoala de soferi";
        Date noTimeDate = new Date(2007,12,11);

        try {
            dateService.getDateMergedWithTime(time, noTimeDate);
            assert false;
        }
        catch (NumberFormatException e){
            assert true;
        }
    }
    @Test
    void bvaTest1() {
        String time = "11:";
        Date noTimeDate = new Date(2006,07,12);

        try {
            dateService.getDateMergedWithTime(time, noTimeDate);
            System.out.println("Test failed");
            assert false;
        }
        catch (ArrayIndexOutOfBoundsException e){
            assert true;
        }

    }



}
