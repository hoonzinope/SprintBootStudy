package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class userAccount {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userID;
    private String password;
    private String name;
    private String level;
    @CreatedDate
    private LocalDateTime reg_date;
    private LocalDateTime update_time;

    public Long getId(){
        return this.id;
    }
}
