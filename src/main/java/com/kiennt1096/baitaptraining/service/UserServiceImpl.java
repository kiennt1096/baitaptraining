package com.kiennt1096.baitaptraining.service;

import com.kiennt1096.baitaptraining.model.DetailUser;
import com.kiennt1096.baitaptraining.model.User;
import com.kiennt1096.baitaptraining.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private DetailUserService detailUserService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
        System.out.println("Done saveUser");
    }


    @Override
    public User getUserById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("User not found by Id: " + id);
        }
        return user;
    }

    @Override
    public boolean findUser(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean findUserByLoginName(String loginName) {
        List<User> userList = this.userRepository.findUserByLoginName(loginName);
        if (userList.isEmpty()) {
            return true;
        } else return false;
    }

    @Override
    public User updateUser(Integer id, User updatedUser) {
        User user = getUserById(id);
        user.setGroup(updatedUser.getGroup());
        user.setLoginName(updatedUser.getLoginName());
        user.setPassword(updatedUser.getPassword());
        user.setFullName(updatedUser.getFullName());
        user.setFullNameKana(updatedUser.getFullNameKana());
        user.setEmail(updatedUser.getEmail());
        user.setTel(updatedUser.getTel());
        user.setBirthday(updatedUser.getBirthday());
        //user.setDetailUser(updatedUser.getDetailUser());
        DetailUser detailUser = detailUserService.saveDetailUser(updatedUser.getDetailUser());
        user.setDetailUser(detailUser);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<User> findUserByName(String name) {
        System.out.println(name);
        if (name != null && name != "") {
            return this.userRepository.findByName(name);
        } else {
            return this.userRepository.findAll();
        }
    }

    @Override
    public List<User> findUserByGroup(Integer groupid) {
        return this.userRepository.findByGroup(groupid);
    }

    @Override
    public List<User> findUserByNameAndGroup(String name, Integer groupid) {
        return this.userRepository.findByNameAndGroup(name, groupid);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepository.findAll(pageable);
    }

}
