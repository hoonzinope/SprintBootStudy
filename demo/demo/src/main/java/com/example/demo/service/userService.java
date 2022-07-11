package com.example.demo.service;

import com.example.demo.DTO.userDTO;
import com.example.demo.domain.userAccount;
import com.example.demo.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    userRepository userRepository;

    public Long joinUser(userDTO udt) {
        userAccount saveUser = udt.toEntity();
        userAccount user = userRepository.save(saveUser);
        return user.getId();
    }

    public Long findUser(userDTO udt) {
        userAccount find = userRepository.selectByIDPW(udt.getUserID(), udt.getPassword());
        if(find != null && find.getId() != null){
            return find.getId();
        }
        return -1L;
    }

}
