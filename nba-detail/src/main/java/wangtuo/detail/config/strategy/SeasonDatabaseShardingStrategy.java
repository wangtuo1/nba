package wangtuo.detail.config.strategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import wangtuo.core.domain.Game;
import wangtuo.core.domain.Season;
import wangtuo.tools.ResponseBody;

public class SeasonDatabaseShardingStrategy implements SingleKeyDatabaseShardingAlgorithm<String> {

	List<Season> seasons;
	Map<Season, List<Game>> seasonsGames = new HashMap<Season, List<Game>>();
	List<Game> games;
	
	@Autowired
	private ObjectMapper mapper;

	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
			ShardingValue<String> shardingValue) {
		return null;
	}

	@Override
	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<String> shardingValue) {
		String targetDatabase = null;
		try {
			targetDatabase = getSeason(shardingValue.getValue()).getName();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String databaseName : availableTargetNames) {
			if (targetDatabase.equals(databaseName)) {
				return databaseName;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames,
			ShardingValue<String> shardingValue) {
		List<String> targetDatabases = new ArrayList<String>();
		for (String seasonId : shardingValue.getValues()) {
			try {
				targetDatabases.add(getSeason(seasonId).getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
		for (String targetDatabase : targetDatabases) {
			for (String databaseName : availableTargetNames) {
				if (databaseName.equals(targetDatabase)) {
					result.add(databaseName);
				}
			}
		}
		return result;
	}
	
	private Season getSeason(String seasonId)
			throws IOException, ClientProtocolException, JsonParseException, JsonMappingException {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://localhost:8080/season/" + seasonId);
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity);
		ResponseBody<Season> responseBody = mapper.readValue(json, new TypeReference<ResponseBody<Season>>() {
		});
		Season season = responseBody.getData();
		return season;
	}
}
