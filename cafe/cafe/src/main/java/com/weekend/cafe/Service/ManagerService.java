package com.weekend.cafe.Service;

import com.weekend.cafe.domain.Manager;
import com.weekend.cafe.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    public Optional<Manager> getManagerById(String managerId) {
        return managerRepository.findByManagerId(managerId);
    }

}
