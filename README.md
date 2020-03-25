# xubxWorkSpace
springboot 整合jta-atomikos实现多数据源及分布式事务管理
引入jar包，各数据库分别定义DataSource、SqlSessionFactory、SqlSessionTemplate，无需定义DataSourceTransactionManager，
把DataSourceTransactionManager交由AtomikosDataSourceBean（在DataSource中实现）来管理

# 添加log4j2日志
    -application.properties 配合 log4j.xml使用
        添加log4j2依赖，屏蔽springboot原始日志依赖
        
# 添加 myBatis sql 拦截器
    MyBatisConfig3、MyBatisConfig4添加interceptor插件。添加myInterceptor类，支持query、update拦截
    
