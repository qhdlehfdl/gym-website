package com.study.shoppingmall.repository;

import com.study.shoppingmall.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MachineRepository extends JpaRepository<Machine,Integer> {

}
