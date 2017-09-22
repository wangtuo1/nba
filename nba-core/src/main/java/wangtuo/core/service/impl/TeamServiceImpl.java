package wangtuo.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wangtuo.core.domain.Team;
import wangtuo.core.service.TeamService;

@Service
@Transactional
public class TeamServiceImpl extends BaseServiceImpl<Team> implements TeamService {

	@Override
	protected String getId(Team t) {
		return t.getId();
	}

	@Autowired
	@Override
	public void setRepository(JpaRepository<Team, String> repository) {
		// TODO Auto-generated method stub
		super.setRepository(repository);
	}
}
