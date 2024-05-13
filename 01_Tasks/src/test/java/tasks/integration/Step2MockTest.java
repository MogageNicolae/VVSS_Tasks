package tasks.integration;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.when;

public class Step2MockTest {

    private TasksService service;
    @Mock
    private ArrayTaskList mockTasks;
    @Mock
    private Task task1,task2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TasksService(mockTasks);
    }

    @Test
    void testGetObservableList1() {
        when(mockTasks.getAll()).thenReturn(List.of(task1, task2));
        ObservableList<Task> observableList = service.getObservableList();
        assertEquals(2, observableList.size());
        assertTrue(observableList.contains(task1));
        assertTrue(observableList.contains(task2));
    }

    @Test
    void testGetObservableList2(){
        when(mockTasks.getAll()).thenReturn(Arrays.asList());
        ObservableList<Task> observableList = service.getObservableList();
        assertEquals(0, observableList.size());
        assertFalse(observableList.contains(task1));
        assertFalse(observableList.contains(task2));
    }

}
