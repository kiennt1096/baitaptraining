package com.kiennt1096.baitaptraining.repository;

import com.kiennt1096.baitaptraining.model.DetailUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailUserRepository extends JpaRepository<DetailUser, Integer> {

}
