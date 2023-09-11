package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import model.Reserva;
import utils.ParseUtils;

public class ReservaDao {

	private final EntityManager entityManager;

	public ReservaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void create(Reserva reserva) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(reserva);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Reserva findById(Long id) {
		return entityManager.find(Reserva.class, id);
	}
	
	public Reserva findLastInsertedRecord() {
        TypedQuery<Reserva> query = entityManager.createQuery(
            "SELECT e FROM Reserva e ORDER BY e.id DESC", Reserva.class);
        query.setMaxResults(1); // Limit the result to one record
        List<Reserva> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
	
	public List<Reserva> findAll() {
		return entityManager.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
	}
	
	public List<Reserva> findByNameOrId(String searchTerm) {
		Long id = null;
		
		if (searchTerm == null || searchTerm.isBlank() || searchTerm.isEmpty()) {
			return findAll();
		}
		
		id = ParseUtils.tryParseLong(searchTerm);
		if(id != null) {
			Reserva reserva = findById(id);
			return reserva != null ? Arrays.asList(reserva) : new ArrayList<>();
		}
		
        return findByName(searchTerm);
    }
	
	private List<Reserva> findByName(String nombre){
		StringBuilder jpql = new StringBuilder("Select r FROM Reserva r JOIN r.guests h WHERE h.nombre LIKE :nombre ");
		TypedQuery<Reserva> query = entityManager.createQuery(jpql.toString(), Reserva.class);
		query.setParameter("nombre", "%" + nombre + "%");
		return query.getResultList();
	}

	

	public void update(Reserva reserva) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(reserva);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void delete(Long id) {
		EntityTransaction transaction = null;

		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			Reserva reserva = entityManager.find(Reserva.class, id);
			if (reserva != null) {
				entityManager.remove(reserva);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}

	public void close() {
		entityManager.close();
	}
}
