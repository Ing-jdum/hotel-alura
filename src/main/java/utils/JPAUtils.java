package utils;

import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JPAUtils {
	
	private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("hotel-alura");
	
	private JPAUtils() {};
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
	
	public static <T> T performTransaction(EntityManager entityManager, Function<EntityManager, T> action) {
        EntityTransaction transaction = entityManager.getTransaction();
        T result = null;

        try {
            transaction.begin();
            result = action.apply(entityManager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } 

        return result;
    }
	
	public static <T> Optional<T> executeQuerySafely(TypedQuery<T> query) {
		try {
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}
}