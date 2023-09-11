package controller;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.User;

public class UserDao {

	private EntityManager em;

	public UserDao(EntityManager em) {
		this.em = em;
	}

	public void createUser(User user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public User getUserByUsername(String username) {
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public User getUserByEmail(String email) {
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
	
	public boolean userExists(String email, String userName) {
		TypedQuery<Long> query = em.createQuery(
	            "SELECT COUNT(u) FROM User u WHERE u.email = :email OR u.userName = :userName", Long.class);
	        
	        query.setParameter("email", email);
	        query.setParameter("userName", userName);
	        
	        Long count = query.getSingleResult();
	  
	        return count > 0;
	}
}
