package xubx.datasource;


import java.sql.SQLException;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import xubx.config.DBConfig3;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.datasource
 * 类名称:     MyBatisConfig1
 * 类描述:     类功能描述
 * 创建人:     xubx
 * 创建时间:   2019/12/16 15:41
 */

@Configuration
// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@MapperScan(basePackages = "xubx.mapper1", sqlSessionTemplateRef = "test3SqlSessionTemplate")
public class MyBatisConfig3 {
    // 配置数据源
    @Bean(name = "test3DataSource")
    public DataSource test3DataSource(DBConfig3 test3Config) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(test3Config.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(test3Config.getPassword());
        mysqlXaDataSource.setUser(test3Config.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("test3DataSource");

        xaDataSource.setMinPoolSize(test3Config.getMinPoolSize());
        xaDataSource.setMaxPoolSize(test3Config.getMaxPoolSize());
        xaDataSource.setMaxLifetime(test3Config.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(test3Config.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(test3Config.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(test3Config.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(test3Config.getMaxIdleTime());
        xaDataSource.setTestQuery(test3Config.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "test3SqlSessionFactory")
    public SqlSessionFactory test3SqlSessionFactory(@Qualifier("test3DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/*Mapper.xml"));
        bean.setPlugins(new Interceptor[]{new MyInterceptor()});
        return bean.getObject();
    }

    @Bean(name = "test3SqlSessionTemplate")
    public SqlSessionTemplate test3SqlSessionTemplate(
            @Qualifier("test3SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
