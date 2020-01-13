package de.michaelevelt.toolcollection.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

public class ChainBuilder<T> {

    private List<ResponsibilityHandler<T>> handlerList;

    private ChainBuilder() {
        handlerList = new ArrayList<>();
    }

    public static <T> ChainBuilder<T> builder() {
        return new ChainBuilder<>();
    }

    public ChainBuilder<T> add(ResponsibilityHandler<T> handler) {
        this.handlerList.add(handler);
        return this;
    }

    public ChainBuilder<T> addAll(List<ResponsibilityHandler<T>> handlerList) {
        this.handlerList.addAll(handlerList);
        return this;
    }

    public ChainOfResponsibility<T> build() {
        return new ChainOfResponsibility<>(handlerList);
    }

}
