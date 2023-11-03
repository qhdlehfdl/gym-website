package com.study.shoppingmall.Service;

import com.study.shoppingmall.entity.Cart;
import com.study.shoppingmall.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart putInCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart findCart(Cart cart) {
        return cartRepository.findByUserIDAndMachineID(cart.getUserID(), cart.getMachineID());
    }

    public void removeCart(Integer id) {
        cartRepository.deleteById(id);
    }

    public List<Integer> findMachineID(Integer userID) {
        //userID로 user가 장바구니에 넣은 machineID 리턴
        return cartRepository.findMachineID(userID);
    }

    public Integer machineCartCount(Integer machineID) {
        //machineID로 search -> 개수 리턴
        //좋아요 개수
        return cartRepository.machineCartCount(machineID);
    }


}
