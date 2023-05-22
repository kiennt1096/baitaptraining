package com.kiennt1096.baitaptraining.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tbl_detail_user_japan")
public class DetailUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_user_japan_id")
    private Integer detailUserId;

    @ManyToOne
    @JoinColumn(name = "code_level")
    private JapaneseLevel japaneseLevel;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "total")
    private Integer total;

    public DetailUser() {
    }

    public DetailUser(Integer detailUserId, JapaneseLevel japaneseLevel, Date startDate, Date endDate, Integer total) {
        this.detailUserId = detailUserId;
        this.japaneseLevel = japaneseLevel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.total = total;
    }

    public Integer getDetailUserId() {
        return detailUserId;
    }

    public void setDetailUserId(Integer detailUserId) {
        this.detailUserId = detailUserId;
    }

    public JapaneseLevel getJapaneseLevel() {
        return japaneseLevel;
    }

    public void setJapaneseLevel(JapaneseLevel japaneseLevel) {
        this.japaneseLevel = japaneseLevel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
