package com.dtsvn.vbcwe.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dtsvn.vbcwe.entity.InEmplNvmtEntity;

@Repository
public interface InEmplNvmtRepository extends JpaRepository<InEmplNvmtEntity, UUID> {

	InEmplNvmtEntity findByUserId(String userId);

}
