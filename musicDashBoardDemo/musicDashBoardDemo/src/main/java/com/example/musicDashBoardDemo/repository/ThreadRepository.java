package com.example.musicDashBoardDemo.repository;

import com.example.musicDashBoardDemo.entity.ThreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<ThreadEntity, Long> {
}
