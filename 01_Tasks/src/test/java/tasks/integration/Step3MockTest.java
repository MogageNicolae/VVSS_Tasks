package tasks.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.*;

public class Step3MockTest {

    private TasksService service;
    private ArrayTaskList tasks;
    private Task task1, task2;

    @BeforeEach
    void setUp() {
        try {
            var start = new SimpleDateFormat("dd/MM/yyyy, HH:mm").parse("01/01/2021, 12:00");
            var end = new SimpleDateFormat("dd/MM/yyyy, HH:mm").parse("02/01/2021, 13:00");
            task1 = new Task("Task 1", start, end, 1);
            task2 = new Task("Task 2", start, end, 1);
            task1.setActive(true);
            task2.setActive(true);
        } catch (Exception e) {
            fail("Exception thrown");
        }

        tasks = new ArrayTaskList();
        service = new TasksService(tasks);
        tasks.add(task1);
        tasks.add(task2);
    }

    @Test
    void testFilterTasks() {
        Date start = null;
        Date end = null;
        try {
            start = new SimpleDateFormat("dd/MM/yyyy, HH:mm").parse("01/01/2021, 11:00");
            end = new SimpleDateFormat("dd/MM/yyyy, HH:mm").parse("01/01/2021, 12:30");
        } catch (Exception e) {
            fail("Exception thrown");
        }
        var filtered = (List<Task>) service.filterTasks(start, end);
        assertEquals(2, filtered.size());
        assertTrue(filtered.contains(task1));
    }

    @Test
    void testDeleteTask() {
        service.deleteTask(task1);
        assertFalse(tasks.getAll().contains(task1));

    }


}
