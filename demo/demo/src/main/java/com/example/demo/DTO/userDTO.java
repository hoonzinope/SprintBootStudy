package com.example.demo.DTO;

import com.example.demo.domain.userAccount;
import lombok.*;

import java.time.LocalDateTime;

@Data //(getter, setter, toString, EqualsAndHashCode, RequiredArgsConstructor 합쳐놓은거)
public class userDTO {

    private String userID;
    private String password;
    private String name;
    private String level;
    private LocalDateTime reg_date;
    private LocalDateTime update_time;

    public userDTO(String userID, String password, String name) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        if(this.level == null) { this.level = "USER"; }
        if(this.reg_date == null) { this.reg_date = LocalDateTime.now(); }
        if(this.update_time == null) { this.update_time = LocalDateTime.now(); }
    }

    public userAccount toEntity() {
        return userAccount.builder()
                .userID(this.userID)
                .password(this.password)
                .name(this.name)
                .level(this.level)
                .reg_date(this.reg_date)
                .update_time(this.update_time)
                .build();
    }
    public userAccount toEntity(userDTO udt) {
        return userAccount.builder()
                .userID(udt.getUserID())
                .password(udt.getPassword())
                .name(udt.getName())
                .level(udt.getLevel())
                .reg_date(udt.getReg_date())
                .update_time(udt.getUpdate_time())
                .build();
    }
}
