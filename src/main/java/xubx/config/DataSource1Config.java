//package xubx.config;
//
//import javax.sql.DataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
///**
// * 项目名称:   pinkstone
// * 包:        xubx.config
// * 类名称:     DataSource1Config
// * 类描述:     读取DataSource01数据源
// * 创建人:     xubx
// * 创建时间:   2019/12/16 14:37
// */
//
//@Configuration // 注册到springboot容器中
//@MapperScan(basePackages = "xubx.mapper1", sqlSessionFactoryRef = "test1SqlSessionFactory")
//public class DataSource1Config {
//    /**
//     * @methodDesc: 功能描述:(配置test01数据库)
//     */
//    @Bean(name = "test1DataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.test1")
//    public DataSource testDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    /**
//     * @methodDesc: 功能描述:(test1 sql会话工厂)
//     */
//    @Bean(name = "test1SqlSessionFactory")
//    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)
//            throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }
//
//    /**
//     * @methodDesc: 功能描述:(test1 事物管理)
//     */
//    @Bean(name = "test1TransactionManager")
//    public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "test1SqlSessionTemplate")
//    public SqlSessionTemplate testSqlSessionTemplate(
//            @Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
