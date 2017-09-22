package wangtuo.detail.config;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import wangtuo.core.domain.Game;
import wangtuo.core.domain.Season;
import wangtuo.detail.config.strategy.GameTableShardingStrategy;
import wangtuo.detail.config.strategy.SeasonDatabaseShardingStrategy;
import wangtuo.tools.ResponseBody;

@Configuration
public class DataSourceConfig {

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String baseUrl;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

	List<Season> seasons;
	Map<Season, List<Game>> seasonsGames = new HashMap<Season, List<Game>>();
	List<Game> games;

	@Bean
	public DataSource dataSource() throws ClientProtocolException, IOException, SQLException {
		return buildDataSource();
	}

	@Autowired
	private ObjectMapper mapper;

	private DataSource buildDataSource() throws ClientProtocolException, IOException, SQLException {
		Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
		//获取所有赛季
		seasons = getSeasons();
		//添加每个赛季对应数据库
		for (Season season : seasons) {
			dataSourceMap.put(season.getName(), createDataSource(season.getName()));
		}
		DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "2016-2017");
		//获取所有比赛信息，添加每场比赛对应的表
		List<String> gameTableNames = new ArrayList<String>();
		for (Game game : getGames()) {
			gameTableNames.add(game.getSeason().getName() + ".game_movement_" + game.getId());
		}
		//添加表规则
		TableRule gameTableRule = TableRule.builder("game_movement").actualTables(gameTableNames)
				.dataSourceRule(dataSourceRule)
				.databaseShardingStrategy(
						new DatabaseShardingStrategy("season_id", new SeasonDatabaseShardingStrategy()))
				.tableShardingStrategy(new TableShardingStrategy("game_id", new GameTableShardingStrategy())).build();
		//添加数据库规则
		ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule)
				.tableRules(Arrays.asList(gameTableRule))
				.databaseShardingStrategy(
						new DatabaseShardingStrategy("season_id", new SeasonDatabaseShardingStrategy()))
				.tableShardingStrategy(new TableShardingStrategy("game_id", new GameTableShardingStrategy())).build();
		DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
		return dataSource;
	}

	private List<Season> getSeasons()
			throws IOException, ClientProtocolException, JsonParseException, JsonMappingException {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http:localhost:8080/season");
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity);
		ResponseBody<List<Season>> responseBody = mapper.readValue(json,
				new TypeReference<ResponseBody<List<Season>>>() {
				});
		List<Season> seasons = responseBody.getData();
		return seasons;
	}

	private List<Game> getGames()
			throws IOException, ClientProtocolException, JsonParseException, JsonMappingException {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http:localhost:8080/game");
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity);
		ResponseBody<List<Game>> responseBody = mapper.readValue(json, new TypeReference<ResponseBody<List<Game>>>() {
		});
		games = responseBody.getData();
		for (Game game : games) {
			Season season = game.getSeason();
			if (null == seasonsGames.get(season)) {
				seasonsGames.put(season, new ArrayList<Game>());
			}
			seasonsGames.get(season).add(game);

		}
		return games;
	}

	private DataSource createDataSource(String dataSourceName) {
		DruidDataSource result = new DruidDataSource();
		result.setDriverClassName(driverClassName);
		result.setUrl(baseUrl + dataSourceName);
		result.setUsername(username);
		result.setPassword(password);
		return result;
	}
}
