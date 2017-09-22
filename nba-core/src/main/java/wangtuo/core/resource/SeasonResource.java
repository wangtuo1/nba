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

import wangtuo.core.domain.Season;
import wangtuo.core.service.SeasonService;
import wangtuo.tools.BindingResultValidator;
import wangtuo.tools.ResponseBody;
import wangtuo.tools.exception.ResultNotFoundException;

@RestController
@RequestMapping("/season")
public class SeasonResource {
	
	@Autowired
	private SeasonService service;

	public SeasonService getService() {
		return service;
	}

	public void setService(SeasonService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public ResponseBody<Season> findOne(@PathVariable String id){
		Season result = service.findOne(id);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Season>(result);
	}
	
	@GetMapping
	public ResponseBody<Page<Season>> findAll(Pageable page) {
		Page<Season> result = service.findAll(page);
		if (null == result || 0 == result.getTotalElements()) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Page<Season>>(result);
	}
	
	@PostMapping
	public ResponseBody<Season> save(@Valid @RequestBody Season season,BindingResult bindingResult){
		BindingResultValidator.checkBindingResult(bindingResult);
		Season result = service.save(season);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Season>(result);
	}
	
	@PutMapping
	public ResponseBody<Season> update(@Valid @RequestBody Season season,BindingResult bindingResult){
		BindingResultValidator.checkBindingResult(bindingResult);
		Season result = service.update(season);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Season>(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseBody<Void> delete(@PathVariable String id){
		service.delete(id);
		return new ResponseBody<Void>(null);
	}
}
