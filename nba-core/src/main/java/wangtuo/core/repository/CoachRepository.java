package wangtuo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import wangtuo.core.domain.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach, String>{

}
