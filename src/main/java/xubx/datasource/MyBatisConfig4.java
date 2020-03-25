package xubx.datasource;


import java.sql.SQLException;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import xubx.config.DBConfig4;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.datasource
 * 类名称:     MyBatisConfig4
 * 类描述:     类功能描述
 * 创建人:     xubx
 * 创建时间:   2019/12/16 15:50
 */

@Configuration
@MapperScan(basePackages = "xubx.mapper2", sqlSessionTemplateRef = "test4SqlSessionTemplate")
public class MyBatisConfig4 {
    // 配置数据源
    @Bean(name = "test4DataSource")
    public DataSource test4DataSource(DBConfig4 test4Config) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(test4Config.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(test4Config.getPassword());
        mysqlXaDataSource.setUser(test4Config.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("test4DataSource");

        xaDataSource.setMinPoolSize(test4Config.getMinPoolSize());
        xaDataSource.setMaxPoolSize(test4Config.getMaxPoolSize());
        xaDataSource.setMaxLifetime(test4Config.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(test4Config.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(test4Config.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(test4Config.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(test4Config.getMaxIdleTime());
        xaDataSource.setTestQuery(test4Config.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "test4SqlSessionFactory")
    public SqlSessionFactory test4SqlSessionFactory(@Qualifier("test4DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/*Mapper.xml"));
        bean.setPlugins(new Interceptor[]{new MyInterceptorAll()});
        return bean.getObject();
    }

    @Bean(name = "test4SqlSessionTemplate")
    public SqlSessionTemplate test4SqlSessionTemplate(
            @Qualifier("test4SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
