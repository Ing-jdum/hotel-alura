package controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Salt;
import model.User;
import utils.JPAUtils;

public class UserDao extends GenericDao<User, Long> {
	private static final String SELECT_USER_BY_USERNAME = "SELECT u FROM User u WHERE u.userName = :username";
	private static final String SELECT_USER_BY_EMAIL = "SELECT u FROM User u WHERE u.email = :email";
	private static final String CHECK_USER_EXISTENCE = "SELECT COUNT(u) FROM User u WHERE u.email = :email OR u.userName = :userName";

	public UserDao(EntityManager entityManager) {
		super(User.class, entityManager);
	}

	public Optional<User> getUserByUsername(String username) {
		TypedQuery<User> query = entityManager.createQuery(SELECT_USER_BY_USERNAME, User.class);
		query.setParameter("username", username);
		return JPAUtils.executeQuerySafely(query);
	}

	public Optional<User> getUserByEmail(String email) {
		TypedQuery<User> query = entityManager.createQuery(SELECT_USER_BY_EMAIL, User.class);
		query.setParameter("email", email);
		return JPAUtils.executeQuerySafely(query);
	}

	public boolean userExists(String email, String userName) {
		TypedQuery<Long> query = entityManager.createQuery(CHECK_USER_EXISTENCE, Long.class);
		query.setParameter("email", email);
		query.setParameter("userName", userName);

		Long count = query.getSingleResult();
		return count > 0;
	}
	
	@Override
	public void update(User entity) {
	    JPAUtils.performTransaction(entityManager, em -> {
	        // Retrieve the existing Salt entity associated with the User
	        Salt existingSalt = entity.getSalt();
	        
	        // Merge the Salt entity to ensure it's in the persistence context
	        Salt mergedSalt = em.merge(existingSalt);
	        
	        // Set the merged Salt entity back to the User
	        entity.setSalt(mergedSalt);
	        
	        // Merge the User entity
	        em.merge(entity);
	        
	        return null;
	    });
	}

}
