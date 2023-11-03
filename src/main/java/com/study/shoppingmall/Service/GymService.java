package com.study.shoppingmall.Service;

import com.study.shoppingmall.entity.Gym;
import com.study.shoppingmall.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService {
    @Autowired
    private GymRepository gymRepository;

    public void loadGymAction(Gym gym) {
        gymRepository.save(gym);
    }

    public List<Gym> findAll(){
        return gymRepository.findAll();
    }

    public Gym findGym(Integer gymID) {
        return gymRepository.findById(gymID).get();
    }
}
