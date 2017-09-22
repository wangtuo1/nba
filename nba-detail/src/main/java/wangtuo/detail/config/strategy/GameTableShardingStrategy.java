package wangtuo.detail.config.strategy;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

public class GameTableShardingStrategy implements SingleKeyTableShardingAlgorithm<String> {

	@Override
	public Collection<String> doBetweenSharding(Collection<String> tableNames,
			ShardingValue<String> shardingValue) {
		return null;
	}

	@Override
	public String doEqualSharding(Collection<String> tableNames, ShardingValue<String> gameId) {
		for (String tableName : tableNames) {
			if (("game_movement_" + gameId.getValue()).equals(tableName)) {
				return tableName;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
		Collection<String> result = new LinkedHashSet<>(tableNames.size());
		for (String value : shardingValue.getValues()) {
			for (String tableName : tableNames) {
				if (tableName.equals(value)) {
					result.add(tableName);
				}
			}
		}
		return result;
	}
}