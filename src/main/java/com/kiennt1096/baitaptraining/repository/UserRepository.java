package com.kiennt1096.baitaptraining.repository;

import com.kiennt1096.baitaptraining.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLoginName(String loginName);
//    @Query(value = "SELECT * FROM tbl_user WHERE fullname like :fullname", nativeQuery = true)
//    List<User> findByName(@Param("fullname") String fullname);
//
//    @Query(value = "SELECT * FROM tbl_user WHERE group_id= :groupid", nativeQuery = true)
//    List<User> findByGroup(@Param("groupid") Integer groupid);
//
//    @Query(value = "SELECT * FROM tbl_user WHERE fullname like :fullname and group_id= :groupid", nativeQuery = true)
//    List<User> findByNameAndGroup(@Param("fullname") String fullname, @Param("groupid") Integer groupid);

    @Query(value = "SELECT * FROM baitaptraining.tbl_user WHERE login_name = :loginname", nativeQuery = true)
    List<User> findUserByLoginName(@Param("loginname") String loginname);
    Page<User> findByFullNameLike(String fullname, Pageable pageable);
    Page<User> findByGroupGroupId(int groupId, Pageable pageable);
    Page<User> findByFullNameLikeAndGroupGroupId(String fullname, int groupId, Pageable pageable);





}
