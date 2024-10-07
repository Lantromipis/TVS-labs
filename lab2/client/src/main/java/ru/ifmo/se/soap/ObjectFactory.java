
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

    private static final QName _AddHero_QNAME = new QName("http://soap.se.ifmo.ru/", "addHero");
    private static final QName _AddHeroResponse_QNAME = new QName("http://soap.se.ifmo.ru/", "addHeroResponse");
    private static final QName _DeleteHero_QNAME = new QName("http://soap.se.ifmo.ru/", "deleteHero");
    private static final QName _DeleteHeroResponse_QNAME = new QName("http://soap.se.ifmo.ru/", "deleteHeroResponse");
    private static final QName _ListHeroes_QNAME = new QName("http://soap.se.ifmo.ru/", "listHeroes");
    private static final QName _ListHeroesResponse_QNAME = new QName("http://soap.se.ifmo.ru/", "listHeroesResponse");
    private static final QName _UpdateHero_QNAME = new QName("http://soap.se.ifmo.ru/", "updateHero");
    private static final QName _UpdateHeroResponse_QNAME = new QName("http://soap.se.ifmo.ru/", "updateHeroResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.ifmo.se.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddHero }
     * 
     */
    public AddHero createAddHero() {
        return new AddHero();
    }

    /**
     * Create an instance of {@link AddHeroResponse }
     * 
     */
    public AddHeroResponse createAddHeroResponse() {
        return new AddHeroResponse();
    }

    /**
     * Create an instance of {@link DeleteHero }
     * 
     */
    public DeleteHero createDeleteHero() {
        return new DeleteHero();
    }

    /**
     * Create an instance of {@link DeleteHeroResponse }
     * 
     */
    public DeleteHeroResponse createDeleteHeroResponse() {
        return new DeleteHeroResponse();
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
     * Create an instance of {@link UpdateHero }
     * 
     */
    public UpdateHero createUpdateHero() {
        return new UpdateHero();
    }

    /**
     * Create an instance of {@link UpdateHeroResponse }
     * 
     */
    public UpdateHeroResponse createUpdateHeroResponse() {
        return new UpdateHeroResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AddHero }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddHero }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.se.ifmo.ru/", name = "addHero")
    public JAXBElement<AddHero> createAddHero(AddHero value) {
        return new JAXBElement<AddHero>(_AddHero_QNAME, AddHero.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddHeroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AddHeroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.se.ifmo.ru/", name = "addHeroResponse")
    public JAXBElement<AddHeroResponse> createAddHeroResponse(AddHeroResponse value) {
        return new JAXBElement<AddHeroResponse>(_AddHeroResponse_QNAME, AddHeroResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteHero }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteHero }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.se.ifmo.ru/", name = "deleteHero")
    public JAXBElement<DeleteHero> createDeleteHero(DeleteHero value) {
        return new JAXBElement<DeleteHero>(_DeleteHero_QNAME, DeleteHero.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteHeroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DeleteHeroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.se.ifmo.ru/", name = "deleteHeroResponse")
    public JAXBElement<DeleteHeroResponse> createDeleteHeroResponse(DeleteHeroResponse value) {
        return new JAXBElement<DeleteHeroResponse>(_DeleteHeroResponse_QNAME, DeleteHeroResponse.class, null, value);
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateHero }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateHero }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.se.ifmo.ru/", name = "updateHero")
    public JAXBElement<UpdateHero> createUpdateHero(UpdateHero value) {
        return new JAXBElement<UpdateHero>(_UpdateHero_QNAME, UpdateHero.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateHeroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UpdateHeroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.se.ifmo.ru/", name = "updateHeroResponse")
    public JAXBElement<UpdateHeroResponse> createUpdateHeroResponse(UpdateHeroResponse value) {
        return new JAXBElement<UpdateHeroResponse>(_UpdateHeroResponse_QNAME, UpdateHeroResponse.class, null, value);
    }

}
