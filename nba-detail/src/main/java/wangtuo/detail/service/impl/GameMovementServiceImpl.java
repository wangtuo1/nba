package wangtuo.detail.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wangtuo.detail.domain.GameMovement;
import wangtuo.detail.repository.GameMovementRepository;
import wangtuo.detail.service.GameMovementService;

@Service
@Transactional
public class GameMovementServiceImpl extends BaseServiceAbstractImpl<GameMovement> implements GameMovementService{

	@Override
	protected String getId(GameMovement t) {
		return t.getId();
	}
	
	@Resource(name="gameMovementRepository")
	@Override
	public void setRepository(JpaRepository<GameMovement, String> repository) {
		// TODO Auto-generated method stub
		super.setRepository(repository);
	}

	@Override
	public List<GameMovement> findBySeasonIdAndGameId(String seasonId, String gameId) {
		return ((GameMovementRepository)repository).findBySeasonIdAndGameId(seasonId, gameId);
	}
}
