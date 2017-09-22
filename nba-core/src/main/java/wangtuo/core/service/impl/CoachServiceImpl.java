package wangtuo.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wangtuo.core.domain.Coach;
import wangtuo.core.service.CoachService;

@Service
@Transactional
public class CoachServiceImpl extends BaseServiceImpl<Coach> implements CoachService {

	@Override
	protected String getId(Coach t) {
		return t.getId();
	}

	@Autowired
	@Override
	public void setRepository(JpaRepository<Coach, String> repository) {
		// TODO Auto-generated method stub
		super.setRepository(repository);
	}
	
	

}
