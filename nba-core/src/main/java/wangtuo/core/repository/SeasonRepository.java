package wangtuo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wangtuo.core.domain.Season;

@Repository
public interface SeasonRepository extends JpaRepository<Season, String>{
	Season findByName(String name);
}
