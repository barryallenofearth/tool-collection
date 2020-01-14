package de.michaelevelt.toolcollection.chainofresponsibility;

import java.util.List;

public class ChainOfResponsibility<T> {

    private List<ResponsibilityHandler<T>> list;

    public ChainOfResponsibility(List<ResponsibilityHandler<T>> list) {
        this.list = list;
    }

    public void handle(T controller) {
        for (ResponsibilityHandler<T> responsibilityHandler : list) {
            if (responsibilityHandler.isResponsibleFor(controller)) {

                if(!responsibilityHandler.handleAndContinue(controller)) {
                    break;
                }

            }
        }
    }

}
