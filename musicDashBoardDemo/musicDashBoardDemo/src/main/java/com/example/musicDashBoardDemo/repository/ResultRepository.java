package com.example.musicDashBoardDemo.repository;

import com.example.musicDashBoardDemo.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    public Optional<Result> findByResultId(String result_id);
}
