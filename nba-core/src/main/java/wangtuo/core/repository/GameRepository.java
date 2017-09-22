package wangtuo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wangtuo.core.domain.Game;


@Repository
public interface GameRepository extends JpaRepository<Game, String> {
	
}
