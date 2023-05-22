package com.kiennt1096.baitaptraining.service;

import com.kiennt1096.baitaptraining.model.DetailUser;
import com.kiennt1096.baitaptraining.repository.DetailUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailUserServiceImpl implements DetailUserService {
    @Autowired
    DetailUserRepository detailUserRepository;

    @Override
    public DetailUser getDetailUserById(Integer detailUserId) {
        Optional<DetailUser> optionalDetailUser = detailUserRepository.findById(detailUserId);
        DetailUser detailUser = null;
        if (optionalDetailUser.isPresent()) {
            detailUser = optionalDetailUser.get();
        } else {
            throw new RuntimeException("DetailUser not found by Id: " + detailUserId);
        }

        return detailUser;
    }

    @Override
    public DetailUser saveDetailUser(DetailUser detailUser) {
        System.out.println("Done detailUserService.saveDetailUser(detailUser);");
        return this.detailUserRepository.save(detailUser);

    }

    @Override
    public void deleteDetailUserById(Integer id) {
        this.detailUserRepository.deleteById(id);
    }
}
