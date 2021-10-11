package main.java.com.epam.library.builder;

import main.java.com.epam.library.entity.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DomBuilder {
    private static final int DEFAULT_DISCOUNT = 0;
    private final DocumentBuilder documentBuilder;
    List<Publication> publicationList;

    public DomBuilder() throws Exception {
        publicationList = new ArrayList<>();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new Exception("DomParserConfigurationException", e);
        }
    }

    public List<Publication> getPublicationList() {
        return new ArrayList<>(publicationList);
    }

    public void buildPublicationSet(String xmlFileName) throws Exception {
        Document document;
        StringBuilder errorMessage = new StringBuilder();

        try {
            document = documentBuilder.parse(String.valueOf(xmlFileName));
            Element root = document.getDocumentElement();

            NodeList bookList = root.getElementsByTagName(PublicationType.BOOK.toString());
            NodeList newspaperList = root.getElementsByTagName(PublicationType.NEWSPAPER.toString());

            for (int i = 0; i < bookList.getLength(); i++) {
                Element element = (Element) bookList.item(i);
                Publication publication = buildPublication(element);
                publicationList.add(publication);
            }
            for (int i = 0; i < newspaperList.getLength(); i++) {
                Element element = (Element) newspaperList.item(i);
                Publication publication = buildPublication(element);
                publicationList.add(publication);
            }
        } catch (IOException e) {
            errorMessage.append("file ").append(xmlFileName).append(" can't be found or opened");
            throw new Exception(errorMessage.toString(), e);

        } catch (SAXException e) {
            errorMessage.append("file ").append(xmlFileName).append(" can't be parsed");
            throw new Exception(errorMessage.toString(), e);
        }
    }

    private Publication buildPublication(Element element) throws Exception {
        PublicationType publicationTag = null;
        StringBuilder errorPublicationTagMessage = new StringBuilder();

        try {
            publicationTag = PublicationType.valueOf(element.getTagName().toUpperCase());
        } catch (IllegalArgumentException e) {
            errorPublicationTagMessage
                    .append("Illegal publicationTag: ")
                    .append(element.getTagName());
            throw new Exception(errorPublicationTagMessage.toString(), e);
        }

        Publication publication = null;
        switch (publicationTag) {
            case BOOK:
                publication = new Book(); // if publication is Book
                break;
            case NEWSPAPER:
                publication = new Newspaper(); // if publication is Newspaper
                break;
        }
        return buildPublicationFactory(element, publication);
    }

    private Publication buildPublicationFactory(Element element, Publication publication) {

        String fTitle = (getElementTextContent(element, XmlTagType.TITLE.toString()));
        publication.setTitle(fTitle);

        String fPages = getElementTextContent(element, XmlTagType.PAGES.toString());
        publication.setPages(Integer.parseInt(fPages));

        switch (PublicationType.valueOf(element.getTagName().toUpperCase())) {
            case BOOK:
                buildPublicationFactoryBook(element, (Book) publication);
                break;
            case NEWSPAPER:
                buildLotFactoryNewspaper(element, (Newspaper) publication);
                break;
            default:
        }
        return publication;
    }

    private void buildPublicationFactoryBook(Element element, Book publication) {
        String fAuthor = getElementTextContent(element, XmlTagType.AUTHOR.toString()).toUpperCase();
        publication.setAuthor(fAuthor);

        String fGenre = getElementTextContent(element, XmlTagType.GENRE.toString()).toUpperCase();
        publication.setGenre(GenreType.valueOf(fGenre));

        String fYear = getElementTextContent(element, XmlTagType.YEAR.toString()).toUpperCase();
        publication.setYear(Year.parse(fYear));

    }

    private void buildLotFactoryNewspaper(Element element, Newspaper publication) {
        String fPublisher = getElementTextContent(element, XmlTagType.PUBLISHER.toString()).toUpperCase();
        publication.setPublisher(fPublisher);

        String fWebSite = getElementTextContent(element, XmlTagType.WEBSITE.toString()).toUpperCase();
        publication.setWebsite(fWebSite);

        String fDate = getElementTextContent(element, XmlTagType.DATE.toString()).toUpperCase();
        publication.setDate(LocalDate.parse(fDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
