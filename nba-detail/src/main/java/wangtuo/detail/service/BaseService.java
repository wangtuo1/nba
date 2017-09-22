package wangtuo.detail.service;

import java.util.List;

public interface BaseService<T> {
	T save(T t);

	T update(T t);

	void delete(String id);

	T findOne(String id);

	List<T> findAll();
}
