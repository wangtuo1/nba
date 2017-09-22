package wangtuo.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wangtuo.core.domain.Court;
import wangtuo.core.service.CourtService;

@Service
@Transactional
public class CourtServiceImpl extends BaseServiceImpl<Court> implements CourtService {

	@Override
	protected String getId(Court t) {
		return t.getId();
	}

	@Autowired
	@Override
	public void setRepository(JpaRepository<Court, String> repository) {
		// TODO Auto-generated method stub
		super.setRepository(repository);
	}
}
