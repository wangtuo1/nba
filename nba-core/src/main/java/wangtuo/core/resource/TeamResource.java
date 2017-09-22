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

import wangtuo.core.domain.Team;
import wangtuo.core.service.TeamService;
import wangtuo.tools.BindingResultValidator;
import wangtuo.tools.ResponseBody;
import wangtuo.tools.exception.ResultNotFoundException;

@RestController
@RequestMapping("/team")
public class TeamResource {

	@Autowired
	private TeamService service;

	public TeamService getService() {
		return service;
	}

	public void setService(TeamService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseBody<Team> findOne(@PathVariable String id) {
		Team result = service.findOne(id);
		if (null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Team>(result);
	}

	@GetMapping
	public ResponseBody<Page<Team>> findAll(Pageable page) {
		Page<Team> result = service.findAll(page);
		if (null == result || 0 == result.getTotalElements()) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Page<Team>>(result);
	}

	@PostMapping
	public ResponseBody<Team> save(@Valid @RequestBody Team team, BindingResult bindingResult) {
		BindingResultValidator.checkBindingResult(bindingResult);
		Team result = service.save(team);
		if (null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Team>(result);
	}

	@PutMapping
	public ResponseBody<Team> update(@Valid @RequestBody Team team, BindingResult bindingResult) {
		BindingResultValidator.checkBindingResult(bindingResult);
		Team result = service.update(team);
		if (null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Team>(result);
	}

	@DeleteMapping("/{id}")
	public ResponseBody<Void> delete(@PathVariable String id) {
		service.delete(id);
		return new ResponseBody<Void>(null);
	}
}
