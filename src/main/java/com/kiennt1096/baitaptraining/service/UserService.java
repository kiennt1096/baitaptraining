package com.kiennt1096.baitaptraining.service;

import com.kiennt1096.baitaptraining.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void saveUser(User user);

    User getUserById(Integer id);

    boolean findUser(Integer id);

    boolean findUserByLoginName(String loginName);

    User updateUser(Integer id, User updatedUser);

    void deleteUserById(Integer id);

    List<User> findUserByName(String name);

    List<User> findUserByGroup(Integer groupid);

    List<User> findUserByNameAndGroup(String name, Integer groupid);

    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


}
