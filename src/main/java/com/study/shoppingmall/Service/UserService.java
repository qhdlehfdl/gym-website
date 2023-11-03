package com.study.shoppingmall.Service;

import com.study.shoppingmall.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.shoppingmall.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User userLogin(String userID, String userPassword) {
        return userRepository.findByUserIDAndUserPassword(userID, userPassword);
    }
    public boolean IDCheck(String userID){
        if(userRepository.findByUserID(userID)==null)
            return false;
        else return true;
    }
    public void userJoin(User user){
        userRepository.save(user);
    }

    public User findUser(String userID) {
        return userRepository.findByUserID(userID);
    }

}
