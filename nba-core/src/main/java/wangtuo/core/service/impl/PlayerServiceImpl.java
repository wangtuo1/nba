package wangtuo.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wangtuo.core.domain.Player;
import wangtuo.core.service.PlayerService;

@Service
@Transactional
public class PlayerServiceImpl extends BaseServiceImpl<Player> implements PlayerService {

	@Override
	protected String getId(Player t) {
		return t.getId();
	}
	
	@Autowired
	@Override
	public void setRepository(JpaRepository<Player, String> repository) {
		// TODO Auto-generated method stub
		super.setRepository(repository);
	}
}
