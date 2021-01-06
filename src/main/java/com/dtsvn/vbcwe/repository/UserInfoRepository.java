package com.dtsvn.vbcwe.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dtsvn.vbcwe.entity.UserInfoEntity;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, UUID> {

	UserInfoEntity findByUserId(String userId);

}
