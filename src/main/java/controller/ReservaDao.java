package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Reserva;
import utils.ParseUtils;

public class ReservaDao extends GenericDao<Reserva, Long> {

	public ReservaDao(EntityManager entityManager) {
		super(Reserva.class, entityManager);
	}

	public Reserva findLastInsertedRecord() {
		TypedQuery<Reserva> query = entityManager.createQuery("SELECT e FROM Reserva e ORDER BY e.id DESC",
				Reserva.class);
		query.setMaxResults(1); // Limit the result to one record
		List<Reserva> resultList = query.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	public List<Reserva> findAll() {
		return entityManager.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
	}

	public List<Reserva> findByNameOrId(String searchTerm) {
		if (searchTerm == null || searchTerm.isBlank() || searchTerm.isEmpty()) {
			return findAll();
		}

		Long id = ParseUtils.tryParseLong(searchTerm);
		if (id != null) {
			Optional<Reserva> reserva = findById(id);
			return reserva.map(Arrays::asList).orElseGet(ArrayList::new);
		}

		return findByName(searchTerm);
	}

	private List<Reserva> findByName(String nombre) {
		StringBuilder jpql = new StringBuilder("Select r FROM Reserva r JOIN r.guests h WHERE h.nombre LIKE :nombre ");
		TypedQuery<Reserva> query = entityManager.createQuery(jpql.toString(), Reserva.class);
		query.setParameter("nombre", "%" + nombre + "%");
		return query.getResultList();
	}
}
