package tasks.services;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TaskServiceMockTest {

    @Mock
    private ArrayTaskList mockTasks;

    private TasksService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TasksService(mockTasks);
    }

    @Test
    void testGetObservableList() {
        var dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
        Task task1 = null;
        Task task2 = null;
        try {
            task1 = new Task("Task 1", dateFormat.parse("01/01/2021, 12:00"));
            task2 = new Task("Task 2", dateFormat.parse("01/01/2021, 13:00"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        when(mockTasks.getAll()).thenReturn(List.of(task1, task2));

        ObservableList<Task> observableList = service.getObservableList();
        assertEquals(2, observableList.size());
        assertTrue(observableList.contains(task1));
        assertTrue(observableList.contains(task2));
    }

    @Test
    void testParseFromStringToSeconds() {
        int seconds = service.parseFromStringToSeconds("01:00");
        assertEquals(3600, seconds);
    }
}
