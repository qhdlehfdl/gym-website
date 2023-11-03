package com.study.shoppingmall.Service;

import com.study.shoppingmall.entity.Machine;
import com.study.shoppingmall.repository.MachineRepository;
import com.study.shoppingmall.repository.MachineRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class MachineService {
    @Autowired
    MachineRepository machineRepository;

    @Autowired
    MachineRepositorySupport machineRepositorySupport;

    public Page<Machine> machineList(Pageable pageable) {
        return machineRepository.findAll(pageable);
    }

    public Page<Machine> machineSearchList(Integer part, String brand, Integer minPrice, Integer maxPrice, Pageable pageable) {
        return machineRepositorySupport.findFilterMachine(pageable, part, brand, minPrice, maxPrice);
    }

    public void write(Machine machine, MultipartFile file) throws Exception{
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";

        UUID uuid=UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        machine.setFileName(fileName);
        machine.setFilePath("/images/" + fileName);

        machineRepository.save(machine);
    }

    public Machine viewMachine(Integer id) {
        return machineRepository.findById(id).get();
    }
}
