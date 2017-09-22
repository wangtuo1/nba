package wangtuo.detail.service;

import java.util.List;

import wangtuo.core.service.BaseService;
import wangtuo.detail.domain.GameMovement;

public interface GameMovementService extends BaseService<GameMovement>{
	List<GameMovement> findBySeasonIdAndGameId(String seasonId,String gameId);
}
