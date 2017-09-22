package wangtuo.core.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import wangtuo.core.service.BaseService;

/**
 * service基础实现
 * @author wangtuo0k
 *
 * @param <T> entity
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	protected JpaRepository<T, String> repository;

	protected Class<T> clazz;

	protected String clazzName;

	public void setRepository(JpaRepository<T, String> repository) {
		this.repository = repository;
	}

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
		clazzName = clazz.getSimpleName();
	}

	public T findOne(String id) {
		return repository.findOne(id);
	}

	public List<T> findAll() {
		return repository.findAll();
	}

	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public T save(T t) {
		return repository.save(t);
	}

	public T update(T t) {
		if (null != findOne(getId(t))) {
			return repository.save(t);
		}
		return null;
	}

	public void delete(String id) {
		if (null == findOne(id)) {
			throw new RuntimeException("can not find " + clazzName + " by id " + id);
		}
		repository.delete(id);
	}

	/**
	 * 获取entity t的主键
	 * @param t
	 * @return
	 */
	protected abstract String getId(T t);
}
