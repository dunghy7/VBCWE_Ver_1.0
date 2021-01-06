package com.dtsvn.vbcwe.dao.common;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.defaults.DefaultSqlSession.StrictMap;

import com.dtsvn.vbcwe.common.Constant;
import com.dtsvn.vbcwe.common.anotation.Column;
import com.dtsvn.vbcwe.common.anotation.PrimaryKey;
import com.dtsvn.vbcwe.common.anotation.Table;

public class BaseProvider {

	// common column
	/** CREATE_USER. */
	private static final String CREATE_USER_FIELD = "createUser";

	/** CREATE_TIME. */
	private static final String CREATE_TIME_FIELD = "createTime";

	/** DELETE_FLAG. */
	private static final String DELETE_FLAG_FIELD = "deleteFlg";

	/**
	 * RECORD_VERSION
	 */
	private static final String RECORD_VERSION_FIELD = "recordVersion";

	// SQL config

	/** FORMAT_STRING_SQL_3 */
	private static final String FORMAT_STRING_SQL_3 = "#{";

	/** STRING_COMMA */
	private static final String STRING_COMMA = ",";

	/** STRING_AS */
	private static final String STRING_AS = " AS ";

	/** FORMAT_STRING_SQL_2 */
	private static final String FORMAT_STRING_SQL_2 = "}";

	/** FORMAT_STRING_SQL_1 */
	private static final String FORMAT_STRING_SQL_1 = " = #{";
	/** FORMAT_STRING_SQL_1 */
	private static final String FORMAT_STRING_SQL_NOT_EQUAL = " <> #{";
	/** SQL_COUNT_ALL_RECODE */
	private static final String SQL_COUNT_ALL_RECODE = "count(*)";

	/** FORMAT_STRING_SQL_4 */
	private static final String FORMAT_STRING_SQL_4 = " = \'";

	/** FORMAT_STRING_SQL_5 */
	private static final String FORMAT_STRING_SQL_5 = "\'";

	/** INSERT_INTO */
	private static final String INSERT_INTO = "INTO ";

	/** INSERT_ALL */
	private static final String INSERT_ALL = "INSERT ALL ";

	/** INSERT_ALL */
	private static final String INSERT_ALL_END = "SELECT * FROM DUAL";

	/** BEGIN */
	private static final String BEGIN_UPDATE_LIST = "BEGIN ";

	/** BEGIN */
	private static final String END_UPDATE_LIST = "END;";

	/** VALUE_START */
	private static final String VALUE_START = " ( ";

	/** VALUE_END */
	private static final String VALUE_END = " ) ";

	/** COLLECTION_LIST */
	private static final String COLLECTION_LIST = "collection[{0}].";

	/** VALUES */
	private static final String VALUES = " VALUES ";

	/** UPDATE Query */
	private static final String UPDATE_QUERY = "UPDATE {0} SET ";

	/** WHERE Query */
	private static final String WHERE_QUERY = " WHERE ";

	/** AND name */
	private static final String AND_NAME = " AND ";

	/** AS_NAME. */
	private static final String AS_NAME = " AS name";

	/** AS_CODE. */
	private static final String AS_CODE = " AS code";

	/**
	 * DELETE_FLG_IS_AVAILABLE
	 */
	private static final String DELETE_FLG_IS_AVAILABLE = "DELETE_FLG = 0";

	/**
	 * record version increase
	 */
	private static final String RECORD_VERSION_INCREASE = "RECORD_VERSION = RECORD_VERSION + 1";

	/**
	 * <p>
	 * 説明 : select by primaryKey
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity エンティティ
	 * @return the string
	 */
	public String selectByPrimaryKey(Object entity) {
		Class<? extends Object> table = entity.getClass();
		String tableName = table.getAnnotation(Table.class).name();

		String sql = new SQL() {
			{
				String selectClause = "";
				FROM(tableName);

				for (Field field : table.getSuperclass().getDeclaredFields()) {
					selectClause = genarateSelectedField(selectClause, field);

				}

				for (Field field : table.getDeclaredFields()) {
					selectClause = genarateSelectedField(selectClause, field);
				}
				selectClause = selectClause.substring(0, selectClause.length() - 1);
				SELECT(selectClause);
			}

			private String genarateSelectedField(String selectClause, Field field) {
				if (field.isAnnotationPresent(PrimaryKey.class)) {
					WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName()
							+ FORMAT_STRING_SQL_2);
					selectClause = selectClause + field.getAnnotation(PrimaryKey.class).name() + STRING_AS
							+ field.getName() + STRING_COMMA;
				} else if (field.isAnnotationPresent(Column.class)) {
					selectClause = selectClause + field.getAnnotation(Column.class).name() + STRING_AS + field.getName()
							+ STRING_COMMA;
				}
				return selectClause;
			}
		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : select by primaryKey
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity エンティティ
	 * @return the string
	 */
	public String selectByPrimaryKeyDate(Object entity) {
		Class<? extends Object> table = entity.getClass();
		String tableName = table.getAnnotation(Table.class).name();

		String sql = new SQL() {
			{
				String selectClause = "";
				FROM(tableName);

				for (Field field : table.getSuperclass().getDeclaredFields()) {
					selectClause = genarateSelectedField(selectClause, field);

				}

				for (Field field : table.getDeclaredFields()) {
					selectClause = genarateSelectedField(selectClause, field);
				}
				selectClause = selectClause.substring(0, selectClause.length() - 1);
				SELECT(selectClause);
			}

			private String genarateSelectedField(String selectClause, Field field) {
				if (field.isAnnotationPresent(PrimaryKey.class)) {
					String formatParam = "";
					if (field.getType().getName().equals(LocalDateTime.class.getName())) {
						formatParam = FORMAT_STRING_SQL_1 + field.getName() + "," + "jdbcType=TIMESTAMP"
								+ FORMAT_STRING_SQL_2;
					} else {
						formatParam = FORMAT_STRING_SQL_1 + field.getName() + FORMAT_STRING_SQL_2;
					}
					WHERE(field.getAnnotation(PrimaryKey.class).name() + formatParam);
					selectClause = selectClause + field.getAnnotation(PrimaryKey.class).name() + STRING_AS
							+ field.getName() + STRING_COMMA;
				} else if (field.isAnnotationPresent(Column.class)) {
					selectClause = selectClause + field.getAnnotation(Column.class).name() + STRING_AS + field.getName()
							+ STRING_COMMA;
				}
				return selectClause;
			}
		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : select all
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param classType Class<? extends Object>
	 * @return String
	 */
	public String selectAll(Class<? extends Object> classType) {

		Class<? extends Object> table = classType;

		String tableName = table.getAnnotation(Table.class).name();
		String sql = new SQL() {
			{
				String selectClause = "";

				for (Field field : table.getSuperclass().getDeclaredFields()) {
					selectClause = genarateSelectedField(selectClause, field);

				}

				for (Field field : table.getDeclaredFields()) {
					selectClause = genarateSelectedField(selectClause, field);
				}
				selectClause = selectClause.substring(0, selectClause.length() - 1);
				SELECT(selectClause);
				FROM(tableName);

			}

			private String genarateSelectedField(String selectClause, Field field) {
				if (field.isAnnotationPresent(PrimaryKey.class)) {
					selectClause = selectClause + field.getAnnotation(PrimaryKey.class).name() + STRING_AS
							+ field.getName() + STRING_COMMA;
				} else if (field.isAnnotationPresent(Column.class)) {
					selectClause = selectClause + field.getAnnotation(Column.class).name() + STRING_AS + field.getName()
							+ STRING_COMMA;
				}
				return selectClause;
			}
		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : delete logic all
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity クラスエンティティ
	 * @return String
	 */
	public String deleteLogicAll(Object entity) {

		Class<? extends Object> table = entity.getClass();
		String tableName = table.getAnnotation(Table.class).name();

		String sql = new SQL() {
			{
				UPDATE(tableName);
				SET(RECORD_VERSION_INCREASE);
				for (Field field : table.getSuperclass().getDeclaredFields()) {
					if (CREATE_USER_FIELD.equals(field.getName()) || CREATE_TIME_FIELD.equals(field.getName())
							|| RECORD_VERSION_FIELD.equals(field.getName())) {
						continue;
					}

					if (field.isAnnotationPresent(Column.class)) {
						if (DELETE_FLAG_FIELD.equals(field.getName())) {
							SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_4 + Constant.DB_DELETED
									+ FORMAT_STRING_SQL_5);
						} else {
							SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName()
									+ FORMAT_STRING_SQL_2);
						}
					}
				}
			}
		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : exist by example
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity Table Entity
	 * @return True duplicate
	 * @throws Exception : Exception
	 */
	public String existByExample(Object entity) throws Exception {

		Class<? extends Object> table = entity.getClass();

		String tableName = table.getAnnotation(Table.class).name();

		String sql = new SQL() {
			{
				SELECT(SQL_COUNT_ALL_RECODE);
				FROM(tableName);

				for (Field field : entity.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if (field.get(entity) != null) {
						genarateWhereField(field);
					}
				}
			}

			private void genarateWhereField(Field field) {
				if (field.isAnnotationPresent(PrimaryKey.class)) {
					WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_NOT_EQUAL + field.getName()
							+ FORMAT_STRING_SQL_2);
				} else if (field.isAnnotationPresent(Column.class)) {
					WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName()
							+ FORMAT_STRING_SQL_2);
				}
			}

		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : delete by primaryKey
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity Object
	 * @return String
	 */
	public String deleteByPrimaryKey(Object entity) {
		Class<? extends Object> table = entity.getClass();
		String tableName = table.getAnnotation(Table.class).name();

		String sql = new SQL() {
			{
				DELETE_FROM(tableName);
				for (Field field : table.getDeclaredFields()) {
					if (field.isAnnotationPresent(PrimaryKey.class)) {
						WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName()
								+ FORMAT_STRING_SQL_2);
					}
				}

				for (Field field : table.getSuperclass().getDeclaredFields()) {
					if (field.isAnnotationPresent(PrimaryKey.class)) {
						WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName()
								+ FORMAT_STRING_SQL_2);
					}
				}
			}
		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : delete with example
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity Object
	 * @return String
	 * @throws Exception Exception
	 */
	public String deleteWithExample(Object entity) throws Exception {
		Class<? extends Object> table = entity.getClass();
		String tableName = table.getAnnotation(Table.class).name();

		String sql = new SQL() {
			{
				DELETE_FROM(tableName);
				for (Field field : entity.getClass().getDeclaredFields()) {
					genSqlForDeleteWithExample(entity, field);
				}

				for (Field field : entity.getClass().getSuperclass().getDeclaredFields()) {
					genSqlForDeleteWithExample(entity, field);
				}
			}

			private void genSqlForDeleteWithExample(Object entity, Field field) throws IllegalAccessException {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
					if (field.get(entity) != null) {
						if (field.isAnnotationPresent(Column.class)) {
							WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName()
									+ FORMAT_STRING_SQL_2);
						} else {
							WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName()
									+ FORMAT_STRING_SQL_2);
						}
					}

				}
			}
		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : insert
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity Object
	 * @return String
	 */
	public String insert(Object entity) {
		Class<? extends Object> table = entity.getClass();
		String tableName = table.getAnnotation(Table.class).name();

		String sql = new SQL() {
			{
				INSERT_INTO(tableName);
				for (Field field : table.getDeclaredFields()) {
					genSqlForInsert(field);
				}

				for (Field field : table.getSuperclass().getDeclaredFields()) {
					genSqlForInsert(field);
				}
			}

			private void genSqlForInsert(Field field) {
				if (field.isAnnotationPresent(Column.class)) {
					VALUES(field.getAnnotation(Column.class).name(),
							FORMAT_STRING_SQL_3 + field.getName() + FORMAT_STRING_SQL_2);
				} else if (field.isAnnotationPresent(PrimaryKey.class)) {
					if (!field.getAnnotation(PrimaryKey.class).autoIncrement()) {
						VALUES(field.getAnnotation(PrimaryKey.class).name(),
								FORMAT_STRING_SQL_3 + field.getName() + FORMAT_STRING_SQL_2);
					}
				}
			}
		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : update By PK
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity Object
	 * @return String
	 * @throws IllegalAccessException
	 */
	public String updateByPK(Object entity) throws IllegalAccessException {
		Class<? extends Object> table = entity.getClass();
		String tableName = table.getAnnotation(Table.class).name();

		String sql = new SQL() {
			{
				UPDATE(tableName);
				for (Field field : table.getDeclaredFields()) {
					field.setAccessible(true);
					genSqlForUpdate(field);
				}

				for (Field field : table.getSuperclass().getDeclaredFields()) {
					if (CREATE_USER_FIELD.equals(field.getName()) || CREATE_TIME_FIELD.equals(field.getName())) {
						continue;
					}
					field.setAccessible(true);
					genSqlForUpdate(field);
				}
			}

			private void genSqlForUpdate(Field field) throws IllegalArgumentException, IllegalAccessException {
				if (field.isAnnotationPresent(PrimaryKey.class)) {
					WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName()
							+ FORMAT_STRING_SQL_2);
				} else if (field.isAnnotationPresent(Column.class)) {
					SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName()
							+ FORMAT_STRING_SQL_2);
				}
			}
		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : update NotNull By PK
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity Object
	 * @return String
	 * @throws IllegalAccessException
	 */
	public String updateNotNullByPK(Object entity) throws IllegalAccessException {
		Class<? extends Object> table = entity.getClass();
		String tableName = table.getAnnotation(Table.class).name();

		String sql;
		sql = new SQL() {
			{
				UPDATE(tableName);
				for (Field field : table.getDeclaredFields()) {
					field.setAccessible(true);
					genSqlForUpdate(field);
				}

				for (Field field : table.getSuperclass().getDeclaredFields()) {
					if (CREATE_USER_FIELD.equals(field.getName()) || CREATE_TIME_FIELD.equals(field.getName())) {
						continue;
					}

					field.setAccessible(true);
					genSqlForUpdate(field);
				}
			}

			private void genSqlForUpdate(Field field) throws IllegalArgumentException, IllegalAccessException {
				if (field.isAnnotationPresent(PrimaryKey.class)) {
					WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName()
							+ FORMAT_STRING_SQL_2);
				} else if (field.isAnnotationPresent(Column.class)) {
					if (field.get(entity) != null) {
						SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName()
								+ FORMAT_STRING_SQL_2);
					}
				}
			}
		}.toString();

		return sql;
	}

	/**
	 * <p>
	 * 説明 : delete Logic By PK
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity Entity
	 * @return SQL query
	 * @throws IllegalAccessException
	 */
	public String deleteLogicByPK(Object entity) throws IllegalAccessException {
		Class<? extends Object> table = entity.getClass();
		String tableName = table.getAnnotation(Table.class).name();

		String sql = new SQL() {
			{
				UPDATE(tableName);
				SET(RECORD_VERSION_INCREASE);
				for (Field field : table.getDeclaredFields()) {
					if (field.isAnnotationPresent(PrimaryKey.class)) {
						WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1 + field.getName()
								+ FORMAT_STRING_SQL_2);
					}
				}

				for (Field field : table.getSuperclass().getDeclaredFields()) {
					if (CREATE_USER_FIELD.equals(field.getName()) || CREATE_TIME_FIELD.equals(field.getName())
							|| RECORD_VERSION_FIELD.equals(field.getName())) {
						continue;
					}

					if (field.isAnnotationPresent(Column.class)) {
						if (DELETE_FLAG_FIELD.equals(field.getName())) {
							SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_4 + Constant.DB_DELETED
									+ FORMAT_STRING_SQL_5);
						} else {
							SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName()
									+ FORMAT_STRING_SQL_2);
						}
					}
				}
			}
		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : insert List
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entityListObj エンティティリストオブジェクト
	 * @param <T>           : Entity
	 * @return insert query
	 */
	@SuppressWarnings("unchecked")
	public <T> String insertList(Object entityListObj) {
		StrictMap<?> map = (StrictMap<?>) entityListObj;
		List<T> entityList = (List<T>) map.get("collection");

		StringBuilder sql = new StringBuilder();
		sql.append(INSERT_ALL);

		if (entityList.size() > 0) {
//            Class<? extends Object> table = entityList.get(0).getClass();
//
//            // 挿入クエリを取得する
//            sql.append(genSqlInsertHeader(table));

			// ボディを挿入する
			sql.append(genSqlInsertBody(entityList));
		}
		sql.append(INSERT_ALL_END);
		return sql.toString();
	}

	/**
	 * <p>
	 * 説明 : update List
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entityListObj エンティティリスト
	 * @param <T>           : Entity
	 * @return value
	 */
	@SuppressWarnings("unchecked")
	public <T> String updateList(Object entityListObj) {
		StrictMap<?> map = (StrictMap<?>) entityListObj;
		List<T> entityList = (List<T>) map.get("collection");

		StringBuilder sql = new StringBuilder(BEGIN_UPDATE_LIST);
		if (entityList.size() > 0) {
			for (int i = 0; i < entityList.size(); i++) {
				sql.append(genSqlUpdate(entityList.get(i), i));
			}
		}
		sql.append(END_UPDATE_LIST);
		return sql.toString();
	}

	/**
	 * <p>
	 * 説明 : delete Logic With Example
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param entity Entity
	 * @return SQL query
	 * @throws Exception Exception
	 */
	public String deleteLogicWithExample(Object entity) throws Exception {
		Class<? extends Object> table = entity.getClass();
		String tableName = table.getAnnotation(Table.class).name();

		String sql = new SQL() {
			{
				UPDATE(tableName);
				SET(RECORD_VERSION_INCREASE);
				for (Field field : table.getSuperclass().getDeclaredFields()) {
					if (CREATE_USER_FIELD.equals(field.getName()) || CREATE_TIME_FIELD.equals(field.getName())
							|| RECORD_VERSION_FIELD.equals(field.getName())) {
						continue;
					}

					if (field.isAnnotationPresent(Column.class)) {
						if (DELETE_FLAG_FIELD.equals(field.getName())) {
							SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_4 + Constant.DB_DELETED
									+ FORMAT_STRING_SQL_5);
						} else {
							SET(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName()
									+ FORMAT_STRING_SQL_2);
						}
					}
				}

				for (Field field : entity.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
						if (field.get(entity) != null) {
							if (field.isAnnotationPresent(Column.class)) {
								WHERE(field.getAnnotation(Column.class).name() + FORMAT_STRING_SQL_1 + field.getName()
										+ FORMAT_STRING_SQL_2);
							} else {
								WHERE(field.getAnnotation(PrimaryKey.class).name() + FORMAT_STRING_SQL_1
										+ field.getName() + FORMAT_STRING_SQL_2);
							}
						}

					}
				}
			}
		}.toString();
		return sql;
	}

	/**
	 * <p>
	 * 説明 : gen Sql Insert Header
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param obj Class
	 * @param <T> class
	 * @return insert into query
	 */
	private <T> String genSqlInsertHeader(Class<T> obj) {
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append(INSERT_INTO);
		sqlQuery.append(obj.getAnnotation(Table.class).name());
		sqlQuery.append(VALUE_START);

		// コラム設定
		for (Field field : obj.getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
				if (field.getAnnotation(Column.class) == null) {
					sqlQuery.append(field.getAnnotation(PrimaryKey.class).name());
				} else {
					sqlQuery.append(field.getAnnotation(Column.class).name());
				}

				sqlQuery.append(STRING_COMMA);
			}
		}

		for (Field field : obj.getSuperclass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {

				if (field.getAnnotation(Column.class) == null) {
					sqlQuery.append(field.getAnnotation(PrimaryKey.class).name());
				} else {
					sqlQuery.append(field.getAnnotation(Column.class).name());
				}

				if (!RECORD_VERSION_FIELD.equals(field.getName())) {
					sqlQuery.append(STRING_COMMA);
				}
			}
		}

		sqlQuery.append(VALUE_END);
		sqlQuery.append(VALUES);

		return sqlQuery.toString();
	}

	/**
	 * <p>
	 * 説明 : gen Sql Insert Body
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param objList List<T>
	 * @param <T>     class
	 * @return insert body
	 */
	private <T> String genSqlInsertBody(List<T> objList) {
		StringBuilder sqlQuery = new StringBuilder();
		for (int i = 0; i < objList.size(); i++) {

			Class<? extends Object> obj = objList.get(i).getClass();

			// create header
			sqlQuery.append(genSqlInsertHeader(obj));

			sqlQuery.append(VALUE_START);

			// ボディーの設定
			for (Field field : obj.getDeclaredFields()) {
				if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
					sqlQuery.append(FORMAT_STRING_SQL_3);
					sqlQuery.append(MessageFormat.format(COLLECTION_LIST, i));
					sqlQuery.append(field.getName());
					sqlQuery.append(FORMAT_STRING_SQL_2);
					sqlQuery.append(STRING_COMMA);
				}
			}

			for (Field field : obj.getSuperclass().getDeclaredFields()) {
				if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
					sqlQuery.append(FORMAT_STRING_SQL_3);
					sqlQuery.append(MessageFormat.format(COLLECTION_LIST, i));
					sqlQuery.append(field.getName());
					sqlQuery.append(FORMAT_STRING_SQL_2);

					if (!RECORD_VERSION_FIELD.equals(field.getName())) {
						sqlQuery.append(STRING_COMMA);
					}
				}
			}

			sqlQuery.append(VALUE_END);

			// if (objList.size() > 1 && i < objList.size() - 1) {
			// sqlQuery.append(STRING_COMMA);
			// }
		}
		return sqlQuery.toString();
	}

	/**
	 * <p>
	 * 説明 : gen Sql Update
	 * </p>
	 * 
	 * @author minh.ls
	 * @since 2020/07/07
	 * @param obj   Class
	 * @param <T>   Entity class
	 * @param index リストのクラスのインデックス
	 * @return String sql update
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private <T> String genSqlUpdate(Object obj, Integer index) {

		Class<? extends Object> table = obj.getClass();

		StringBuilder query = new StringBuilder();
		StringBuilder setQuery = new StringBuilder();
		StringBuilder whereQuery = new StringBuilder();

		// set update query
		query.append(MessageFormat.format(UPDATE_QUERY, table.getAnnotation(Table.class).name()));

		for (Field field : table.getDeclaredFields()) {
			if (CREATE_USER_FIELD.equals(field.getName()) || CREATE_TIME_FIELD.equals(field.getName())) {
				continue;
			}

			if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
				field.setAccessible(true);

				// gen set data for update
				try {
					if (field.isAnnotationPresent(Column.class) && field.get(obj) != null) {
						setQuery.append(field.getAnnotation(Column.class).name());
						setQuery.append(FORMAT_STRING_SQL_1);
						setQuery.append(MessageFormat.format(COLLECTION_LIST, index));
						setQuery.append(field.getName());
						setQuery.append(FORMAT_STRING_SQL_2);
						setQuery.append(STRING_COMMA);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

				// gen where
				if (field.isAnnotationPresent(PrimaryKey.class)) {
					if (whereQuery.length() > 0) {
						whereQuery.append(AND_NAME);
					}

					whereQuery.append(field.getAnnotation(PrimaryKey.class).name());
					whereQuery.append(FORMAT_STRING_SQL_1);
					whereQuery.append(MessageFormat.format(COLLECTION_LIST, index));
					whereQuery.append(field.getName());
					whereQuery.append(FORMAT_STRING_SQL_2);
				}
			}
		}

		for (Field field : table.getSuperclass().getDeclaredFields()) {
			if (CREATE_USER_FIELD.equals(field.getName()) || CREATE_TIME_FIELD.equals(field.getName())) {
				continue;
			}

			if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(PrimaryKey.class)) {
				field.setAccessible(true);

				// gen set data for update
				try {
					if (field.isAnnotationPresent(Column.class) && field.get(obj) != null) {
						setQuery.append(field.getAnnotation(Column.class).name());
						setQuery.append(FORMAT_STRING_SQL_1);
						setQuery.append(MessageFormat.format(COLLECTION_LIST, index));
						setQuery.append(field.getName());
						setQuery.append(FORMAT_STRING_SQL_2);
						setQuery.append(STRING_COMMA);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

				// gen where
				if (field.isAnnotationPresent(PrimaryKey.class)) {
					if (whereQuery.length() > 0) {
						whereQuery.append(AND_NAME);
					}

					whereQuery.append(field.getAnnotation(PrimaryKey.class).name());
					whereQuery.append(FORMAT_STRING_SQL_1);
					whereQuery.append(MessageFormat.format(COLLECTION_LIST, index));
					whereQuery.append(field.getName());
					whereQuery.append(FORMAT_STRING_SQL_2);
				}
			}

		}

		// set sql query
		String setStr = setQuery.toString();
		query.append(setStr.substring(0, setStr.length() - 1));
		query.append(WHERE_QUERY);
		query.append(whereQuery.toString() + ";");

		return query.toString();
	}

	/**
	 * <p>
	 * Giải thích : SQL lấy data (code, name) đang tồn tại của table, không bao gồm
	 * điều kiện
	 * </p>
	 *
	 * @since 31/07/2020
	 * @param tableNm tên bảng
	 * @param colCode tên cột làm code
	 * @param colName tên cột làm name
	 * @return SQL
	 */
	public String getSelectItem(String tableNm, String colCode, String colName) {
		String sql = new SQL() {
			{
				SELECT(colCode + AS_CODE, colName + AS_NAME);
				FROM(tableNm);
				WHERE(DELETE_FLG_IS_AVAILABLE);

			}
		}.toString();
		return sql;
	}

	/**
	 *
	 * <p>
	 * Giải thích : SQL lấy data (code, name) đang tồn tại của table, bao gồm điều
	 * kiện
	 * </p>
	 * 
	 * @since 31/07/2020
	 * @param tableNm tên bảng
	 * @param colCd   tên cột làm code
	 * @param colNm   tên cột làm name
	 * @param where   chuỗi điều kiện
	 * @return SQL
	 */
	public String getSelectItemWithCondition(String tableNm, String colCd, String colNm, String where) {
		String sql = new SQL() {
			{
				SELECT(colCd + AS_CODE, colNm + AS_NAME);
				FROM(tableNm);
				WHERE(DELETE_FLG_IS_AVAILABLE + String.format(StringUtils.isEmpty(where) ? "" : " AND %s", where));
			}
		}.toString();
		return sql;
	}

	/**
	 *
	 * <p>
	 * Giải thích : SQL lấy data (code, name) đang tồn tại của table, bao gồm điều
	 * kiện, có sắp xếp
	 * </p>
	 * 
	 * @since 31/07/2020
	 * @param tableNm    tên bảng
	 * @param colCd      tên cột làm code
	 * @param colNm      tên cột làm name
	 * @param where      chuỗi điều kiện
	 * @param orderbyCol ORDER BY
	 * @return SQL
	 */
	public String getSelectItemWithConditionOrder(String tableNm, String colCd, String colNm, String where,
			String orderbyCol) {
		String sql = new SQL() {
			{
				SELECT(colCd + AS_CODE, colNm + AS_NAME);
				FROM(tableNm);
				WHERE(DELETE_FLG_IS_AVAILABLE + String.format(StringUtils.isEmpty(where) ? "" : " AND %s", where));
				ORDER_BY(orderbyCol);
			}
		}.toString();
		return sql;
	}

	/**
	 *
	 * <p>
	 * Giải thích : SQL lấy data (name) đang tồn tại của table, không bao gồm điều
	 * kiện
	 * </p>
	 * 
	 * @since 31/07/2020
	 * @param tableNm tên table
	 * @param colNm   tên cột
	 * @return SQL
	 */
	public String getColValue(String tableNm, String colNm) {
		String sql = new SQL() {
			{
				SELECT(colNm + AS_NAME);
				FROM(tableNm);
				WHERE(DELETE_FLG_IS_AVAILABLE);
			}
		}.toString();
		return sql;
	}

	/**
	 *
	 * <p>
	 * Giải thích : SQL lấy data (name) đang tồn tại của table, không bao gồm điều
	 * kiện
	 * </p>
	 * 
	 * @since 31/07/2020
	 * @param tableNm tên table
	 * @param colNm   tên cột
	 * @param where   chuỗi điều kiện
	 * @return SQL
	 */
	public String getColValueWithCondition(String tableNm, String colNm, String where) {
		String sql = new SQL() {
			{
				SELECT(colNm + AS_NAME);
				FROM(tableNm);
				WHERE(DELETE_FLG_IS_AVAILABLE + String.format(StringUtils.isEmpty(where) ? "" : " AND %s", where));
			}
		}.toString();
		return sql;
	}

	/**
	 *
	 * <p>
	 * Giải thích : SQL lấy data (name) đang tồn tại của table, không bao gồm điều
	 * kiện, có sắp xếp
	 * </p>
	 * 
	 * @since 31/07/2020
	 * @param tableNm tên table
	 * @param colNm   tên cột
	 * @param where   chuỗi điều kiện
	 * @param order   order by
	 * @return SQL
	 */
	public String getColValueWithConditionOrder(String tableNm, String colNm, String where, String order) {
		String sql = new SQL() {
			{
				SELECT(colNm + AS_NAME);
				FROM(tableNm);
				WHERE(DELETE_FLG_IS_AVAILABLE + String.format(StringUtils.isEmpty(where) ? "" : " AND %s", where));
				ORDER_BY(order);
			}
		}.toString();
		return sql;
	}

	/**
	 *
	 * <p>
	 * Giải thích : Sql lấy all data (name) của table, không bao gồm điều kiện
	 * </p>
	 * 
	 * @since 31/07/2020
	 * @param tblName tên table
	 * @return SQL
	 */
	public String selectAllWithoutAnotation(String tblName) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(tblName);
			}
		}.toString();
		return sql;
	}

	/**
	 *
	 * <p>
	 * Giải thích : SQL check tồn tại data, bao gồm điều kiện
	 * </p>
	 * 
	 * @since 31/07/2020
	 * @param tblName tên table
	 * @param where   điều kiện
	 * @return SQL
	 */
	public String checkExistsWithCondition(String tblName, String where) {
		String sql = new SQL() {
			{
				SELECT(SQL_COUNT_ALL_RECODE);
				FROM(tblName);
				WHERE(DELETE_FLG_IS_AVAILABLE + String.format(StringUtils.isEmpty(where) ? "" : " AND %s", where));
			}
		}.toString();
		return sql;
	}
}