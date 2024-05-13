package tasks.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTaskListTest {


    @Mock
    private Task task1;

    @Mock
    private Task task2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd1() {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        arrayTaskList.add(task1);
        List<Task> tasks = arrayTaskList.getAll();

        assertEquals(1, tasks.size());
        assertSame(task1, tasks.get(0));
        assertSame(task1, arrayTaskList.getTask(0));
    }

    @Test
    void testAdd2(){
        ArrayTaskList arrayTaskList = new ArrayTaskList();

        arrayTaskList.add(task1);
        arrayTaskList.add(task1);

        arrayTaskList.add(task2);
        List<Task> tasks = arrayTaskList.getAll();

        assertTrue(arrayTaskList.toString().contains("currentCapacity=10"));
        assertEquals(3, tasks.size());
        assertSame(task1, tasks.get(0));
        assertSame(task1, tasks.get(1));
        assertSame(task2, tasks.get(2));

        for (int i = 0; i < 10; i++) {
            arrayTaskList.add(task1);
        }
        tasks = arrayTaskList.getAll();

        assertTrue(arrayTaskList.toString().contains("currentCapacity=20"));
        assertEquals(13, tasks.size());
        arrayTaskList.add(task2);

        tasks = arrayTaskList.getAll();

        assertEquals(14, tasks.size());
        assertSame(task2, tasks.get(13));

    }

    @Test
    void testRemove(){
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);

        List<Task> tasks = arrayTaskList.getAll();
        assertEquals(2, tasks.size());

        assertTrue(arrayTaskList.remove(task1));
        tasks = arrayTaskList.getAll();
        assertEquals(1, tasks.size());
        assertSame(task2, tasks.get(0));

        assertFalse(arrayTaskList.remove(task1));
        tasks = arrayTaskList.getAll();
        assertEquals(1, tasks.size());
        assertSame(task2, tasks.get(0));

        assertTrue(arrayTaskList.remove(task2));
        tasks = arrayTaskList.getAll();
        assertEquals(0, tasks.size());

        assertFalse(arrayTaskList.remove(task2));
        tasks = arrayTaskList.getAll();
        assertEquals(0, tasks.size());
    }



}