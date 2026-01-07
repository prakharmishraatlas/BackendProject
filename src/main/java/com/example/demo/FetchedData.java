// this file tells us the schema of the table in which we are storing the data

package com.example.demo;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity // this is used to tell spring to make a table for this class
@Table (name="FetchedData")
@Data // this helps us prevent writing getter setter methods. Lombok does this for us.
public class FetchedData {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremented counter
    private Long id;
    private String metal;
    private String currency;
    private Double price;
    private Double bid;
    private Double ask;
    private LocalDateTime timestamp;
}