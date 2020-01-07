package xubx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 项目名称:   pinkstone
 * 包:        xubx.config
 * 类名称:     DBConfig3
 * 类描述:     将application.properties配置文件中配置自动封装到实体类字段中
 * 创建人:     xubx
 * 创建时间:   2019/12/16 15:35
 */
@Data
@ConfigurationProperties(prefix = "mysql.datasource.test1") // 注意这个前缀要和application.properties文件的前缀一样
public class DBConfig3 {
    private String url;
    // 比如这个url在properties中是这样子的mysql.datasource.test1.username = root
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;

}
