package com.dtsvn.vbcwe.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dtsvn.vbcwe.entity.UserLoginEntity;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, UUID> {

	UserLoginEntity findByUserId(String userId);

}
