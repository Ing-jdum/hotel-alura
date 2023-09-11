package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Huesped;
import utils.ParseUtils;

public class HuespedDao extends GenericDao<Huesped, Long> {

	private static final String GET_ALL = "SELECT h FROM Huesped h LEFT JOIN FETCH h.reserva";
	private static final String GET_HUESPED_BY_NAME = "Select h FROM Huesped h LEFT JOIN FETCH h.reserva WHERE h.nombre LIKE :name";

	public HuespedDao(EntityManager entityManager) {
		super(Huesped.class, entityManager);
	}

	public List<Huesped> findAll() {
		TypedQuery<Huesped> query = entityManager.createQuery(GET_ALL,
				Huesped.class);
		return query.getResultList();
	}

	public List<Huesped> findByNameOrId(String searchTerm) {
		if (searchTerm == null || searchTerm.isBlank() || searchTerm.isEmpty()) {
			return findAll();
		}

		Long id = ParseUtils.tryParseLong(searchTerm);
		if (id != null) {
			Optional<Huesped> huesped = findById(id);
			return huesped.map(Arrays::asList).orElseGet(ArrayList::new);
		}

		return findByName(searchTerm);
	}

	private List<Huesped> findByName(String name) {
		StringBuilder jpql = new StringBuilder(
				GET_HUESPED_BY_NAME);
		TypedQuery<Huesped> query = entityManager.createQuery(jpql.toString(), Huesped.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}

}
