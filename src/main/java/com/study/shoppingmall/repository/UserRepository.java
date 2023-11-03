package com.study.shoppingmall.repository;

import com.study.shoppingmall.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserIDAndUserPassword(String userID, String userPassword);

    User findByUserID(String userID);
}
