package wangtuo.core.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wangtuo.core.domain.Player;
import wangtuo.core.service.PlayerService;
import wangtuo.tools.BindingResultValidator;
import wangtuo.tools.ResponseBody;
import wangtuo.tools.exception.ResultNotFoundException;

@RestController
@RequestMapping("/player")
public class PlayerResource {
	
	@Autowired
	private PlayerService service;

	public PlayerService getService() {
		return service;
	}

	public void setService(PlayerService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public ResponseBody<Player> findOne(@PathVariable String id){
		Player result = service.findOne(id);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Player>(result);
	}
	
	@GetMapping
	public ResponseBody<Page<Player>> findAll(Pageable page) {
		Page<Player> result = service.findAll(page);
		if (null == result || 0 == result.getTotalElements()) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Page<Player>>(result);
	}
	
	@PostMapping
	public ResponseBody<Player> save(@Valid @RequestBody Player player,BindingResult bindingResult){
		BindingResultValidator.checkBindingResult(bindingResult);
		Player result = service.save(player);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Player>(result);
	}
	
	@PutMapping
	public ResponseBody<Player> update(@Valid @RequestBody Player player,BindingResult bindingResult){
		BindingResultValidator.checkBindingResult(bindingResult);
		Player result = service.update(player);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Player>(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseBody<Void> delete(@PathVariable String id){
		service.delete(id);
		return new ResponseBody<Void>(null);
	}
}
