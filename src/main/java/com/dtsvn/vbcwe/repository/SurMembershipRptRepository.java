package com.dtsvn.vbcwe.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dtsvn.vbcwe.entity.SurMembershipRptEntity;

@Repository
public interface SurMembershipRptRepository extends JpaRepository<SurMembershipRptEntity, UUID> {

	/**
	 * Lấy danh sách bản ghi chờ convert PPTX sang PDF
	 * 
	 * @param status 0 Chờ xử lý
	 * @return danh sách bản ghi chờ xử lý convert
	 */
	List<SurMembershipRptEntity> findByStatusEquals(String status);

}
