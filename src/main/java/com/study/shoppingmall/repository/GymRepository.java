package com.study.shoppingmall.repository;

import com.study.shoppingmall.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym,Integer> {
}
