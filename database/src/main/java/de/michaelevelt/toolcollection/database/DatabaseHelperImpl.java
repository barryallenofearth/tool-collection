package de.michaelevelt.toolcollection.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DatabaseHelperImpl extends SilentDatabaseHelper {

	private static final Logger LOGGER = LogManager.getLogger(DatabaseHelperImpl.class);

	public DatabaseHelperImpl(String persistenceUnitName) {
		super(persistenceUnitName);
	}

	@Override
	public <T> void persist(T tableEntry) {
		LOGGER.debug("Persist Entity " + tableEntry);
		super.persist(tableEntry);
	}

	@Override
	public <T> void merge(T tableEntry) {
		LOGGER.debug("Merge Entity " + tableEntry);
		super.merge(tableEntry);
	}

	@Override
	public <T> void remove(T tableEntry) {
		LOGGER.debug("Remove Entity " + tableEntry);
		super.remove(tableEntry);
	}

}


