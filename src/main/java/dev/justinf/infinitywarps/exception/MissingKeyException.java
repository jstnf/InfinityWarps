package dev.justinf.infinitywarps.exception;

public class MissingKeyException extends Exception {

    public MissingKeyException(String key) {
        super("Missing key in configuration: " + key);
    }
}