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

import wangtuo.core.domain.Game;
import wangtuo.core.service.GameService;
import wangtuo.tools.BindingResultValidator;
import wangtuo.tools.ResponseBody;
import wangtuo.tools.exception.ResultNotFoundException;

@RestController
@RequestMapping("/game")
public class GameResource {
	
	@Autowired
	private GameService service;

	public GameService getService() {
		return service;
	}

	public void setService(GameService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public ResponseBody<Game> findOne(@PathVariable String id){
		Game result = service.findOne(id);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Game>(result);
	}
	
	@GetMapping
	public ResponseBody<Page<Game>> findAll(Pageable page) {
		Page<Game> result = service.findAll(page);
		if (null == result || 0 == result.getTotalElements()) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Page<Game>>(result);
	}
	
	@PostMapping
	public ResponseBody<Game> save(@Valid @RequestBody Game game,BindingResult bindingResult){
		BindingResultValidator.checkBindingResult(bindingResult);
		Game result = service.save(game);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Game>(result);
	}
	
	@PutMapping
	public ResponseBody<Game> update(@Valid @RequestBody Game game,BindingResult bindingResult){
		BindingResultValidator.checkBindingResult(bindingResult);
		Game result = service.update(game);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Game>(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseBody<Void> delete(@PathVariable String id){
		service.delete(id);
		return new ResponseBody<Void>(null);
	}
}
