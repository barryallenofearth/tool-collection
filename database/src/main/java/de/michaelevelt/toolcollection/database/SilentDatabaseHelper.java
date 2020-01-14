package de.michaelevelt.toolcollection.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.BiConsumer;

public class SilentDatabaseHelper implements DatabaseHelper {

	private static EntityManagerFactory entityManagerFactory;

	protected final EntityManager entityManager;

	public SilentDatabaseHelper(String persistenceUnitName) {
		if (entityManagerFactory == null) {
			//Es darf immer nur eine entityManagerFactory geben!
			entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
		}
		entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public <T> void persist(T tableEntry) {
		handleTransaction(tableEntry, (t, entityManager) -> {
			entityManager.persist(t);
			entityManager.flush();
		});
	}

	@Override
	public <T> void merge(T tableEntry) {
		handleTransaction(tableEntry,
				(t, entityManager) -> {
					entityManager.merge(t);
					entityManager.flush();
				});
	}

	@Override
	public <T> void remove(T tableEntry) {
		handleTransaction(
				tableEntry, (t, entityManager) -> {
					if (!entityManager.contains(t)) {
						t = entityManager.merge(t);
					}
					entityManager.remove(t);
				}
		);
	}

	private <T> void handleTransaction(T tableEntry, BiConsumer<T, EntityManager> consumer) {

		try {
			if (!entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().begin();
			}
			consumer.accept(tableEntry, entityManager);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			if (!entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().begin();
			}
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
}
