package org.example.taskorginizer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private long taskId;
    private String taskDescription;
    private Date dueDate;
    private byte [] attachment;
    private  int assignee;
    private Timestamp  createdAt;
    private Timestamp  updatedAt;
    private int updatedBy;
}
