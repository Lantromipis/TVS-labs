package ru.ifmo.se.exception;

public class UnsupportedRsqlOperatorException  extends RuntimeException{
    public UnsupportedRsqlOperatorException(String symbol) {
        super("RSQL operator with symbol '" + symbol + " is not supported.");
    }
}
