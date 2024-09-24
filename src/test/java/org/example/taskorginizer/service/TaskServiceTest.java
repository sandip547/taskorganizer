package org.example.taskorginizer.service;

import org.example.taskorginizer.entity.Task;
import org.example.taskorginizer.repo.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TaskServiceTest {
    @Mock
    private TaskRepo taskRepo;
    @InjectMocks
    private TaskService taskService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks()  {
        Task task = new Task();
        long expectedTaskId = 1L;
        String expectedDescription = "Test Task";
        Date expectedDueDate = new Date();
        byte[] expectedAttachment = {1, 2, 3, 4};
        int expectedAssignee = 1001;
        Timestamp expectedCreatedAt = new Timestamp(System.currentTimeMillis());
        Timestamp expectedUpdatedAt = new Timestamp(System.currentTimeMillis());
        int expectedUpdatedBy = 2001;

        task.setTaskId(expectedTaskId);
        task.setTaskDescription(expectedDescription);
        task.setDueDate(expectedDueDate);
        task.setAttachment(expectedAttachment);
        task.setAssignee(expectedAssignee);
        task.setCreatedAt(expectedCreatedAt);
        task.setUpdatedAt(expectedUpdatedAt);
        task.setUpdatedBy(expectedUpdatedBy);

        assertEquals(expectedTaskId, task.getTaskId());
        assertEquals(expectedDescription, task.getTaskDescription());
        assertEquals(expectedDueDate, task.getDueDate());
        assertArrayEquals(expectedAttachment, task.getAttachment());
        assertEquals(expectedAssignee, task.getAssignee());
        assertEquals(expectedCreatedAt, task.getCreatedAt());
        assertEquals(expectedUpdatedAt, task.getUpdatedAt());
        assertEquals(expectedUpdatedBy, task.getUpdatedBy());


    }
    @Test
    void testAttachmentHandling() {
        Task task = new Task();
        byte[] attachment = {0x12, 0x34, 0x56};
        task.setAttachment(attachment);
        assertNotNull(task.getAttachment());
        assertArrayEquals(attachment, task.getAttachment());
    }

    @Test
    void createTask(){
        String description = "Complete the assignment";
        Date dueDate = new Date();
        byte[] attachment = {1, 2, 3};
        int assignee = 1001;

        Task task = new Task();
        task.setTaskDescription(description);
        task.setDueDate(dueDate);
        task.setAttachment(attachment);
        task.setAssignee(assignee);
        task.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        when(taskRepo.save(any(Task.class))).thenReturn(task);

        Task result = taskService.saveTask(task);

        assertNotNull(result);
        assertEquals(description, result.getTaskDescription());
        assertEquals(dueDate, result.getDueDate());
        assertArrayEquals(attachment, result.getAttachment());
        assertEquals(assignee, result.getAssignee());
        assertNotNull(result.getCreatedAt());
    }

    @Test
    void testUpdateTask() {
        Long taskId = 1L;
        Task existingTask = new Task();
        existingTask.setTaskId(taskId);
        existingTask.setTaskDescription("Complete the service for user");
        existingTask.setDueDate(new Date());
        existingTask.setAttachment(new byte[]{1, 2, 3});
        existingTask.setAssignee(1001);
        existingTask.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        existingTask.setUpdatedBy(2001);

        Task updatedTask = new Task();
        updatedTask.setTaskDescription("Complete the service for the user in 3 hours");
        updatedTask.setDueDate(new Date());
        updatedTask.setAttachment(new byte[]{4, 5, 6});
        updatedTask.setAssignee(2002);
        updatedTask.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        updatedTask.setUpdatedBy(3002);

        when(taskRepo.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepo.save(any(Task.class))).thenReturn(existingTask);

        Task result = taskService.updateTask(taskId, updatedTask);
        assertNotNull(result);
        assertEquals("Complete the service for user", result.getTaskDescription());
        assertArrayEquals(new byte[]{1, 2, 3}, result.getAttachment());
        assertEquals(1001, result.getAssignee());
        assertEquals(2001, result.getUpdatedBy());

        verify(taskRepo, times(1)).save(existingTask);
    }
    @Test
    void testDeleteTaskById() {
        Long taskId = 4L;
        taskService.deleteTask(taskId);
        verify(taskRepo, times(1)).deleteById(taskId);
    }

}
