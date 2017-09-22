package wangtuo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wangtuo.core.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String>{
	Team findByName(String name);
}
