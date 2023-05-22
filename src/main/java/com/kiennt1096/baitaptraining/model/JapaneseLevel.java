package com.kiennt1096.baitaptraining.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mst_japan")
public class JapaneseLevel {

    @Id
    @Column(name = "code_level", length = 15)
    private String codeLevel;

    @Column(name = "name_level", length = 255)
    private String nameLevel;

    public JapaneseLevel() {
    }

    public JapaneseLevel(String codeLevel, String nameLevel) {
        this.codeLevel = codeLevel;
        this.nameLevel = nameLevel;
    }

    public String getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(String codeLevel) {
        this.codeLevel = codeLevel;
    }

    public String getNameLevel() {
        return nameLevel;
    }

    public void setNameLevel(String nameLevel) {
        this.nameLevel = nameLevel;
    }
}
