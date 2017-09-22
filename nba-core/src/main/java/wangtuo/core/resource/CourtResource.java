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

import wangtuo.core.domain.Court;
import wangtuo.core.service.CourtService;
import wangtuo.tools.BindingResultValidator;
import wangtuo.tools.ResponseBody;
import wangtuo.tools.exception.ResultNotFoundException;

@RestController
@RequestMapping("/court")
public class CourtResource {
	
	@Autowired
	private CourtService service;

	public CourtService getService() {
		return service;
	}

	public void setService(CourtService service) {
		this.service = service;
	}
	
	@GetMapping("/{id}")
	public ResponseBody<Court> findOne(@PathVariable String id){
		Court result = service.findOne(id);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Court>(result);
	}
	
	@GetMapping
	public ResponseBody<Page<Court>> findAll(Pageable page) {
		Page<Court> result = service.findAll(page);
		if (null == result || 0 == result.getTotalElements()) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Page<Court>>(result);
	}
	
	@PostMapping
	public ResponseBody<Court> save(@Valid @RequestBody Court court,BindingResult bindingResult){
		BindingResultValidator.checkBindingResult(bindingResult);
		Court result = service.save(court);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Court>(result);
	}
	
	@PutMapping
	public ResponseBody<Court> update(@Valid @RequestBody Court court,BindingResult bindingResult){
		BindingResultValidator.checkBindingResult(bindingResult);
		Court result = service.update(court);
		if(null == result) {
			throw new ResultNotFoundException();
		}
		return new ResponseBody<Court>(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseBody<Void> delete(@PathVariable String id){
		service.delete(id);
		return new ResponseBody<Void>(null);
	}
}
