package wangtuo.detail.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import wangtuo.detail.domain.GameMovement;
import wangtuo.detail.service.GameMovementService;
import wangtuo.tools.ResponseBody;
import wangtuo.tools.exception.RequestBodyInvalidException;
import wangtuo.tools.exception.ResultNotFoundException;

@RestController
@RequestMapping("/gameMovement")
@Api
public class GameMovementResource {

	@Autowired
	private GameMovementService service;

	public GameMovementService getService() {
		return service;
	}

	public void setService(GameMovementService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseBody<GameMovement> findOne(@PathVariable String id) {
		GameMovement result = service.findOne(id);
		if (null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<>(result);
	}

	@GetMapping
	public ResponseBody<List<GameMovement>> findAll(@RequestParam(value = "seasonId", required = false) String seasonId,
			@RequestParam(value = "gameId", required = false) String gameId) {
		List<GameMovement> list;
		if (null != seasonId && null != gameId) {
			list = service.findBySeasonIdAndGameId(seasonId, gameId);
		} else {
			list = service.findAll();
		}
		if (null == list || list.isEmpty()) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<>(list);
	}

	@PostMapping
	public ResponseBody<GameMovement> save(@Valid @RequestBody GameMovement gameMovement, BindingResult bindingResult) {
		checkBindingResult(bindingResult);
		GameMovement result = service.save(gameMovement);
		if (null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<>(result);
	}

	@PutMapping
	public ResponseBody<GameMovement> update(@Valid @RequestBody GameMovement gameMovement,
			BindingResult bindingResult) {
		checkBindingResult(bindingResult);
		GameMovement result = service.update(gameMovement);
		if (null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<>(result);
	}

	@DeleteMapping("/{id}")
	public ResponseBody<Void> delete(@PathVariable String id) {
		service.delete(id);
		return new ResponseBody<Void>(null);
	}

	public void checkBindingResult(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuffer sb = new StringBuffer("request body invalid.");
			for(ObjectError error: bindingResult.getAllErrors()) {
				sb.append(error.getDefaultMessage());
				
			}
//			bindingResult.getAllErrors().forEach(e -> sb.append(e.getDefaultMessage()));
			throw new RequestBodyInvalidException(sb.toString());
		}
	}
}
