package com.dtsvn.vbcwe.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.dtsvn.vbcwe.dao", sqlSessionTemplateRef = "sessionTemplate")
public class VBCWEDataSourceConfig {

	@Bean(name = "dataSource") // As a bean object and named
	@ConfigurationProperties(prefix = "spring.datasource") // In the configuration file, the prefix of the data source
	public DataSource PrimaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "sessionFactory")
	public SqlSessionFactory PrimarySessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage("com.dtsvn.vbcwe.dto");
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlMapper/*.xml"));

		SqlSessionFactory sqlSessionFactory = bean.getObject();
		sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);

		return sqlSessionFactory;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager PrimaryTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sessionTemplate")
	public SqlSessionTemplate PrimarySessionTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory)
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}