package com.it.stack.common;

@SuppressWarnings("serial")
public class StackException extends RuntimeException {

    public StackException(String name) {
        super(name);
    }

    public StackException() {
        super();
    }
}
