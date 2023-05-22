package com.kiennt1096.baitaptraining.service;

import com.kiennt1096.baitaptraining.model.DetailUser;

public interface DetailUserService {
    DetailUser getDetailUserById(Integer detailUserId);

    DetailUser saveDetailUser(DetailUser detailUser);
    DetailUser updateDetailUser(Integer id, DetailUser updatedDetailUser);

    void deleteDetailUserById(Integer id);

}
