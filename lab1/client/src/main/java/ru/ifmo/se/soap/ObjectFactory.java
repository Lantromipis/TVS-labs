
package ru.ifmo.se.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.ifmo.se.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _ListHeroes_QNAME = new QName("http://soap.se.ifmo.ru/", "listHeroes");
    private static final QName _ListHeroesResponse_QNAME = new QName("http://soap.se.ifmo.ru/", "listHeroesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.ifmo.se.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListHeroes }
     * 
     */
    public ListHeroes createListHeroes() {
        return new ListHeroes();
    }

    /**
     * Create an instance of {@link ListHeroesResponse }
     * 
     */
    public ListHeroesResponse createListHeroesResponse() {
        return new ListHeroesResponse();
    }

    /**
     * Create an instance of {@link HeroListRequestDto }
     * 
     */
    public HeroListRequestDto createHeroListRequestDto() {
        return new HeroListRequestDto();
    }

    /**
     * Create an instance of {@link HeroDto }
     * 
     */
    public HeroDto createHeroDto() {
        return new HeroDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListHeroes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListHeroes }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.se.ifmo.ru/", name = "listHeroes")
    public JAXBElement<ListHeroes> createListHeroes(ListHeroes value) {
        return new JAXBElement<ListHeroes>(_ListHeroes_QNAME, ListHeroes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListHeroesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListHeroesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.se.ifmo.ru/", name = "listHeroesResponse")
    public JAXBElement<ListHeroesResponse> createListHeroesResponse(ListHeroesResponse value) {
        return new JAXBElement<ListHeroesResponse>(_ListHeroesResponse_QNAME, ListHeroesResponse.class, null, value);
    }

}
