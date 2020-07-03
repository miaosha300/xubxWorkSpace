# xubxWorkSpace
springboot 整合jta-atomikos实现多数据源及分布式事务管理
引入jar包，各数据库分别定义DataSource、SqlSessionFactory、SqlSessionTemplate，无需定义DataSourceTransactionManager，
把DataSourceTransactionManager交由AtomikosDataSourceBean（在DataSource中实现）来管理

# 添加log4j2日志
    -application.properties 配合 log4j.xml使用
        添加log4j2依赖，屏蔽springboot原始日志依赖
        
# 添加 myBatis sql 拦截器
    MyBatisConfig3、MyBatisConfig4添加interceptor插件。添加myInterceptor类，
    支持query、update拦截（requestMapping /interceptor）
    
# 添加 myBatis sql 拦截器
    MyBatisConfig3、MyBatisConfig4添加interceptor插件。添加myInterceptorAll类，
    支持query、update、insert、update拦截（requestMapping /interceptorAll）
    
# 热部署（参考：https://www.cnblogs.com/mlq2017/p/9633650.html）


# CountDownLatch 
    是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程执行完后再执行。
    例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后执行。
# CountDownlatch 原理
    通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就相应的减1 。
    当计数器到达 0 时，表示所有的线程都已完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
    
    