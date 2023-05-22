package com.kiennt1096.baitaptraining.repository;

import com.kiennt1096.baitaptraining.model.JapaneseLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JapaneseLevelRepository extends JpaRepository<JapaneseLevel, String> {
}
