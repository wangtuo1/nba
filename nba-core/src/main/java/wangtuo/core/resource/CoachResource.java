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

import wangtuo.core.domain.Coach;
import wangtuo.core.service.CoachService;
import wangtuo.tools.BindingResultValidator;
import wangtuo.tools.ResponseBody;
import wangtuo.tools.exception.ResultNotFoundException;

@RestController
@RequestMapping("/coach")
public class CoachResource {
	
	@Autowired
	private CoachService service;

	public CoachService getService() {
		return service;
	}

	public void setService(CoachService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public ResponseBody<Coach> findOne(@PathVariable String id){
		Coach result = service.findOne(id);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Coach>(result);
	}
	
	@GetMapping
	public ResponseBody<Page<Coach>> findAll(Pageable page) {
		Page<Coach> result = service.findAll(page);
		if (null == result || 0 == result.getTotalElements()) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Page<Coach>>(result);
	}
	
	@PostMapping
	public ResponseBody<Coach> save(@Valid @RequestBody Coach coach,BindingResult bindingResult){
		BindingResultValidator.checkBindingResult(bindingResult);
		Coach result = service.save(coach);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Coach>(result);
	}
	
	@PutMapping
	public ResponseBody<Coach> update(@Valid @RequestBody Coach coach,BindingResult bindingResult){
		BindingResultValidator.checkBindingResult(bindingResult);
		Coach result = service.update(coach);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Coach>(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseBody<Void> delete(@PathVariable String id){
		service.delete(id);
		return new ResponseBody<Void>(null);
	}
}
