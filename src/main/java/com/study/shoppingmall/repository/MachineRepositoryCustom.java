package com.study.shoppingmall.repository;

import com.study.shoppingmall.entity.Machine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MachineRepositoryCustom {
    Page<Machine> findFilterMachine(Pageable pageable, Integer part, String brand, Integer minPrice, Integer MaxPrice);
}
