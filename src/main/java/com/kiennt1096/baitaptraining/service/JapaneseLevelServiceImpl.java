package com.kiennt1096.baitaptraining.service;

import com.kiennt1096.baitaptraining.model.Group;
import com.kiennt1096.baitaptraining.model.JapaneseLevel;
import com.kiennt1096.baitaptraining.repository.JapaneseLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JapaneseLevelServiceImpl implements JapaneseLevelService {
    @Autowired
    private JapaneseLevelRepository japaneseLevelRepository;

    @Override
    public JapaneseLevel getJapaneseLevelById(String codeLevel) {
        Optional<JapaneseLevel> japaneseLevelOptional = japaneseLevelRepository.findById(codeLevel);
        JapaneseLevel japaneseLevel = null;
        if (japaneseLevelOptional.isPresent()) {
            japaneseLevel = japaneseLevelOptional.get();
        } else {
            throw new RuntimeException("JapaneseLevel not found by Id: " + codeLevel);
        }
        //System.out.println(japaneseLevel.getNameLevel());
        return japaneseLevel;
    }
}
