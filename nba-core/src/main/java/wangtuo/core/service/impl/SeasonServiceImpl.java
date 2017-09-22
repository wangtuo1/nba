package wangtuo.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wangtuo.core.domain.Season;
import wangtuo.core.service.SeasonService;

@Service
@Transactional
public class SeasonServiceImpl extends BaseServiceImpl<Season> implements SeasonService {

	@Override
	protected String getId(Season t) {
		return t.getId();
	}

	@Autowired
	@Override
	public void setRepository(JpaRepository<Season, String> repository) {
		// TODO Auto-generated method stub
		super.setRepository(repository);
	}
}
