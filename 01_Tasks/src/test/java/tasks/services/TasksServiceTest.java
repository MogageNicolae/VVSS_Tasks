package tasks.services;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TasksServiceTest {
    private static TasksService service;
    private ArrayTaskList tasks;

    @BeforeAll
    static void setUpAll() {
        System.out.println("Initializing Tests...");
        service = new TasksService(null);
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Tests Finished.");
    }

    @BeforeEach
    void setUp() {
        tasks = new ArrayTaskList();
        Task newTask = new Task("Test Task", new Date());
        tasks.add(newTask);
        service.setTasks(tasks);
    }

    @AfterEach
    void tearDown() {
        tasks = null;
    }

    @Test
    void testGetObservableList() {
        ObservableList<Task> observableList = service.getObservableList();
        assertEquals(tasks.getAll(), observableList);
    }

    @Test
    void testGetIntervalInHours() {
        Task task = new Task("Test Task", new Date(), new Date(), 3600);
        String interval = service.getIntervalInHours(task);
        assertEquals("01:00", interval);

    }

    @Test
    void testParseFromStringToSeconds() {
        int seconds = service.parseFromStringToSeconds("01:00");
        assertEquals(3600, seconds);
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("Test Task", new Date());
        Task currentTask = tasks.getAll().get(0);
        tasks.add(task);
        service.deleteTask(task);
        assertFalse(tasks.getAll().contains(task));
        assertTrue(tasks.getAll().contains(currentTask));
        service.deleteTask(currentTask);
        assertFalse(tasks.getAll().contains(currentTask));
    }
}