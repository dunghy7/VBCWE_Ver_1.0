package com.dtsvn.vbcwe.dao.common;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface BaseMapper<T> {

	@InsertProvider(type = BaseProvider.class, method = "insert")
	Integer insert(T record);

	@InsertProvider(type = BaseProvider.class, method = "insertList")
	Integer insertList(List<T> record);

	@UpdateProvider(type = BaseProvider.class, method = "updateByPK")
	Integer update(T record);

	@UpdateProvider(type = BaseProvider.class, method = "updateList")
	Integer updateList(List<T> record);

	@DeleteProvider(type = BaseProvider.class, method = "deleteByPrimaryKey")
	Integer deleteByPK(T record);

	@DeleteProvider(type = BaseProvider.class, method = "deleteWithExample")
	Integer deleteWithExample(T record);

	@SelectProvider(type = BaseProvider.class, method = "existByExample")
	Boolean existByExample(T record);

	@SelectProvider(type = BaseProvider.class, method = "selectByPrimaryKey")
	T selectByPK(T record);

	@SelectProvider(type = BaseProvider.class, method = "selectByPrimaryKeyDate")
	T selectByPKDate(T record);

	@SelectProvider(type = BaseProvider.class, method = "selectAll")
	List<T> selectAll(Class<?> entityClass);

	@UpdateProvider(type = BaseProvider.class, method = "updateNotNullByPK")
	Integer updateNotNullByPK(T record);

	@UpdateProvider(type = BaseProvider.class, method = "deleteLogicByPK")
	Integer deleteLogicByPK(T record);

	@UpdateProvider(type = BaseProvider.class, method = "deleteLogicWithExample")
	Integer deleteLogicWithExample(T record);

	@UpdateProvider(type = BaseProvider.class, method = "deleteLogicAll")
	Integer deleteLogicAll(T record);

}
