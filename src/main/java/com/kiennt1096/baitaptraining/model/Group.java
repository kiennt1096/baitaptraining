package com.kiennt1096.baitaptraining.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mst_group")
public class Group {
    @Id
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "group_name", length = 255, nullable = false)
    private String groupName;

    public Group() {
    }

    public Group(Integer groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
