package wangtuo.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wangtuo.core.domain.Game;
import wangtuo.core.service.GameService;

@Service
@Transactional
public class GameServiceImpl extends BaseServiceImpl<Game> implements GameService {

	@Override
	protected String getId(Game t) {
		return t.getId();
	}

	@Autowired
	@Override
	public void setRepository(JpaRepository<Game, String> repository) {
		// TODO Auto-generated method stub
		super.setRepository(repository);
	}
}
