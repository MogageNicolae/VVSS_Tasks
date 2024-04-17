package tasks.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TasksOperationsTest {

    public ObservableList<Task> getObservableList(ArrayTaskList tasks){
        return FXCollections.observableArrayList(tasks.getAll());
    }
    @Test
    public void testIncoming_noTasks() {
        // Arrange
        TasksOperations tasksOperations = new TasksOperations(getObservableList(new ArrayTaskList()));
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 10000);

        // Act
        Iterable<Task> incomingTasks = tasksOperations.incoming(startDate, endDate );

        // Assert
        assertNotNull(incomingTasks);
        assertFalse(incomingTasks.iterator().hasNext());
    }

    @Test
    public void testIncoming_singleTaskBeforeStart() {
        // Arrange
        var tasksList = new ArrayTaskList();
        tasksList.add(new Task("Task 1", new Date(0)));
        TasksOperations tasksOperations = new TasksOperations(getObservableList(tasksList));
        Date startDate = new Date(1000);
        Date endDate = new Date(startDate.getTime() + 10000);

        // Act
        Iterable<Task> incomingTasks = tasksOperations.incoming(startDate, endDate);

        // Assert
        assertNotNull(incomingTasks);
        Iterator<Task> iterator = incomingTasks.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIncoming_singleTaskWithinRange() {
        // Arrange
        ArrayTaskList tasksList = new ArrayTaskList();
        var task= new Task("Task 1", new Date(5000), new Date(7000), 2);
        task.setActive(true);
        tasksList.add(task);
        TasksOperations tasksOperations = new TasksOperations(getObservableList(tasksList));
        Date startDate = new Date(1000);
        Date endDate = new Date(startDate.getTime() + 10000);

        // Act
        Iterable<Task> incomingTasks = tasksOperations.incoming(startDate, endDate);

        // Assert
        assertNotNull(incomingTasks);
        Iterator<Task> iterator = incomingTasks.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Task 1", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }


    @Test
    public void testIncoming_singleTaskAfterEnd() {
        // Arrange
        var tasksList = new ArrayTaskList();
        tasksList.add(new Task("Task 1", new Date(15000)));
        TasksOperations tasksOperations = new TasksOperations(getObservableList(tasksList));
        Date startDate = new Date(1000);
        Date endDate = new Date(startDate.getTime() + 10000);

        // Act
        Iterable<Task> incomingTasks = tasksOperations.incoming(startDate, endDate);

        // Assert
        assertNotNull(incomingTasks);
        Iterator<Task> iterator = incomingTasks.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIncoming_multipleTasks() {
        // Arrange
        var tasksList = new ArrayTaskList();
        var task1= new Task("Task 1", new Date(2000), new Date(4000), 2);
        task1.setActive(true);
        var task2= new Task("Task 2", new Date(8000), new Date(10000), 2);
        task2.setActive(true);
        var task3 = new Task("Task 3", new Date(15000), new Date(17000), 2);
        task3.setActive(true);
        var task4 = new Task("Task 4", new Date(11000), new Date(13000), 2);
        task4.setActive(true);

        tasksList.add(task1);
        tasksList.add(task2);
        tasksList.add(task3);
        tasksList.add(task4);

        TasksOperations tasksOperations = new TasksOperations(getObservableList(tasksList));
        Date startDate = new Date(1000);
        Date endDate = new Date(startDate.getTime() + 10000);

        // Act
        ArrayList<Task> incomingTasks = (ArrayList<Task>) tasksOperations.incoming(startDate, endDate);

        // Assert
        assertNotNull(incomingTasks);
        Iterator<Task> iterator = incomingTasks.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Task 1", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Task 2", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Task 4", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }
}
