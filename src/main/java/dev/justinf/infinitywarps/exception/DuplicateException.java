package dev.justinf.infinitywarps.exception;

public class DuplicateException extends Exception {

    public DuplicateException(String id) {
        super("An object with id " + id + " already exists!");
    }
}