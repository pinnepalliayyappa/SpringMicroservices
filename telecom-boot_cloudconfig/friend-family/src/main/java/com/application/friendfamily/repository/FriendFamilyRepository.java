package com.application.friendfamily.repository;

import java.util.List;

import com.application.friendfamily.entity.FriendFamily;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FriendFamilyRepository extends JpaRepository<FriendFamily, Integer> {

	List<FriendFamily> findByPhoneNo(long phoneNo);
}
