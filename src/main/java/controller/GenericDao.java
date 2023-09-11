package controller;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.EntityManager;

import utils.JPAUtils;

public abstract class GenericDao<T, ID extends Serializable> {

	private final Class<T> entityClass;
	protected final EntityManager entityManager;

	public GenericDao(Class<T> entityClass, EntityManager entityManager) {
		this.entityClass = entityClass;
		this.entityManager = entityManager;
	}

	public void create(T entity) {
		JPAUtils.performTransaction(entityManager, em -> {
			entityManager.persist(entity);
			return null;
		});
	}

	public Optional<T> findById(ID id) {
		return JPAUtils.performTransaction(entityManager, em -> Optional.ofNullable(em.find(entityClass, id)));
	}

	public void update(T entity) {
		JPAUtils.performTransaction(entityManager, em -> {
			em.merge(entity);
			return null;
		});
	}

	public void delete(ID id) {
		JPAUtils.performTransaction(entityManager, em -> {
			T entity = entityManager.find(entityClass, id);
			if (entity != null) {
				entityManager.remove(entity);
			}
			return null;
		});
	}

}
