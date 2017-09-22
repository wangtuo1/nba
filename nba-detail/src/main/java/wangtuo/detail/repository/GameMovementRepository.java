package wangtuo.detail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wangtuo.detail.domain.GameMovement;
@Repository("gameMovementRepository")
public interface GameMovementRepository extends JpaRepository<GameMovement, String>{
	List<GameMovement> findBySeasonIdAndGameId(String seasonId,String gameId);
}
