package tasks.model;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskMockTest {
    private Task task;
    private SimpleDateFormat dateFormat = Task.getDateFormat();

    @Before
    public void setUp() throws ParseException {
        Date time = dateFormat.parse("2024-05-13 10:00");
        task = new Task("Test Task", time);
    }

    @Test
    public void testConstructorWithTime() {
        assertNotNull(task);
        assertEquals("Test Task", task.getDescription());
        assertEquals("2024-05-13 10:00", dateFormat.format(task.getTime()));
    }

    @Test
    public void testConstructorWithInterval() throws ParseException {
        Date start = dateFormat.parse("2024-05-13 10:00");
        Date end = dateFormat.parse("2024-05-13 11:00");
        Task repeatedTask = new Task("Test Repeated Task", start, end, 3600);
        assertNotNull(repeatedTask);
        assertEquals("Test Repeated Task", repeatedTask.getDescription());
        assertEquals("2024-05-13 10:00", dateFormat.format(repeatedTask.getStartTime()));
        assertEquals("2024-05-13 11:00", dateFormat.format(repeatedTask.getEndTime()));
        assertTrue(repeatedTask.isRepeated());
        assertEquals(3600, repeatedTask.getRepeatInterval());
        assertThrows(IllegalArgumentException.class, ()->new Task("Test Bad Task", end, start, 0));
    }



    @Test
    public void testSetDescription() {
        task.setDescription("New Description");
        assertEquals("New Description", task.getDescription());
    }

    @Test
    public void testIsActive() {
        assertFalse(task.isActive());
        task.setActive(true);
        assertTrue(task.isActive());
    }

    @Test
    public void testFormattedDates() {
        assertEquals("2024-05-13 10:00", task.getFormattedDateStart());
        assertEquals("2024-05-13 10:00", task.getFormattedDateEnd());
    }

    @Test
    public void testEqualsAndHashCode() throws ParseException {
        Date time = dateFormat.parse("2024-05-13 10:00");
        Task equalTask = new Task("Test Task", time);
        assertEquals(task, equalTask);
        assertEquals(task.hashCode(), equalTask.hashCode());

        Task differentTask = new Task("Different Task", time);
        assertNotEquals(task, differentTask);
        assertNotEquals(task.hashCode(), differentTask.hashCode());
    }
}
