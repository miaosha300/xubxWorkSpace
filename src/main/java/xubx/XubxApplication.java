package xubx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import xubx.config.DBConfig3;
import xubx.config.DBConfig4;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableConfigurationProperties(value = {DBConfig3.class, DBConfig4.class})
public class XubxApplication {

	public static void main(String[] args) {
		SpringApplication.run(XubxApplication.class, args);
	}

}
