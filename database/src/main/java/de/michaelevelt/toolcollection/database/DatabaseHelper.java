package de.michaelevelt.toolcollection.database;

public interface DatabaseHelper {

	<T> void persist(T tableEntry);

	<T> void merge(T tableEntry);

	<T> void remove(T tableEntry);

}
