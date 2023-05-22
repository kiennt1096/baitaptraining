package com.kiennt1096.baitaptraining.service;

import com.kiennt1096.baitaptraining.model.Group;
import com.kiennt1096.baitaptraining.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group getGroupById(Integer id) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        Group group = null;
        if (groupOptional.isPresent()) {
            group = groupOptional.get();
        } else {
            throw new RuntimeException("Group not found by Id: " + id);
        }
        //System.out.println(date);

        return group;

    }
}
