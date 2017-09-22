package wangtuo.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * service基础接口
 * @author wangtuo0k
 *
 * @param <T> entity
 */
public interface BaseService<T> {
	T save(T t);

	T update(T t);

	void delete(String id);

	T findOne(String id);

	List<T> findAll();
	
	Page<T> findAll(Pageable pageable);
}
