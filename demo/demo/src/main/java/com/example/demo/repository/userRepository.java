package com.example.demo.repository;

import com.example.demo.domain.userAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface userRepository extends JpaRepository<userAccount, Long> {

    @Query("SELECT user FROM userAccount user WHERE user.userID = :userId AND user.password = :password")
    userAccount selectByIDPW(@Param("userId")String userId, @Param("password") String pw);
}
