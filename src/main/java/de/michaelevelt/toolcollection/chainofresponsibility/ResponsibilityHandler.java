package de.michaelevelt.toolcollection.chainofresponsibility;

public interface ResponsibilityHandler<T> {
	
	boolean isResponsibleFor(T controller);
	boolean handleAndContinue(T controller);

}
