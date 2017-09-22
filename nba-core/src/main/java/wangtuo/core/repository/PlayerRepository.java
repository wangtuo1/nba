package wangtuo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wangtuo.core.domain.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String>{

}
