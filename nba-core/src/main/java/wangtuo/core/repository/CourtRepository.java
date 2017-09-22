package wangtuo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wangtuo.core.domain.Court;

@Repository
public interface CourtRepository extends JpaRepository<Court, String>{
	Court findByName(String name);
}
