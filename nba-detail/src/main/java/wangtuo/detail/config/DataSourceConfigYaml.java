package wangtuo.detail.config;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dangdang.ddframe.rdb.sharding.config.yaml.api.YamlShardingDataSource;

//@Configuration
public class DataSourceConfigYaml {

	@Bean
	public DataSource dataSource() {
		try {
			return new YamlShardingDataSource(
					new File("/shardingjdbc.yaml"));
		} catch (IOException | SQLException e) {
			System.out.println("================================================================");
			return null;
		}
	}
}
