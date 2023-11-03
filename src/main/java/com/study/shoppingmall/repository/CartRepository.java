package com.study.shoppingmall.repository;

import com.study.shoppingmall.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByUserIDAndMachineID(int userID, int machineID);

    @Query(value="select cart.machineID from Cart cart where cart.userID = :userID")
    List<Integer> findMachineID(@Param("userID") Integer userID);


    @Query(value = "select count(*) from Cart cart where cart.machineID = :machineID")
    Integer machineCartCount(@Param("machineID") Integer machineID);
}
