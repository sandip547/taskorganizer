package org.example.taskorginizer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comments {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  long id;
    private String comment;
    private LocalDateTime createdAt;
    private Long taskId;

}
