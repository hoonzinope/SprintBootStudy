package com.example.demo.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class userAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userID;
    private String password;
    private String name;
    private String level;
    private LocalDateTime reg_date;
    private LocalDateTime update_time;

}
