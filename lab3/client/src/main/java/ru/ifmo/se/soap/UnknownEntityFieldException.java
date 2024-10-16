
package ru.ifmo.se.soap;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.6
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "UnknownEntityField", targetNamespace = "http://soap.se.ifmo.ru/")
public class UnknownEntityFieldException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UnknownEntityFieldFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public UnknownEntityFieldException(String message, UnknownEntityFieldFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public UnknownEntityFieldException(String message, UnknownEntityFieldFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: ru.ifmo.se.soap.UnknownEntityFieldFault
     */
    public UnknownEntityFieldFault getFaultInfo() {
        return faultInfo;
    }

}
