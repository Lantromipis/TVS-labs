package ru.ifmo.se.exception;

import lombok.Getter;
import ru.ifmo.se.exception.fault.UnsupportedRsqlOperatorFault;

import javax.xml.ws.WebFault;
import java.io.Serial;

@Getter
@WebFault(name = "UnsupportedRsqlOperator", faultBean = "ru.ifmo.se.exception.fault.UnsupportedRsqlOperatorFault")
public class UnsupportedRsqlOperatorException extends Exception {
    @Serial
    private static final long serialVersionUID = -923394585528818428L;
    private final UnsupportedRsqlOperatorFault faultInfo;

    public UnsupportedRsqlOperatorException(String symbol) {
        super("RSQL operator with symbol '" + symbol + " is not supported.");
        faultInfo = new UnsupportedRsqlOperatorFault(symbol);
    }
}
