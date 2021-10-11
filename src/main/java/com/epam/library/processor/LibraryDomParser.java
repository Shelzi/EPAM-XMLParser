package main.java.com.epam.library.processor;

import java.util.List;

import main.java.com.epam.library.builder.DomBuilder;
import main.java.com.epam.library.entity.Publication;

public class LibraryDomParser {

    private static final String XML_FILE_NAME = "src/main/resources/bookStructure.xml";
    private static final String XSD_FILE_NAME = "src/main/resources/bookStructure.xsd";

    public static void main(String[] args) throws Exception {
        //if (XmlValidator.isXmlValid(XML_FILE_NAME, XSD_FILE_NAME)) {
            DomBuilder domBuilder = new DomBuilder();
            domBuilder.buildPublicationSet(XML_FILE_NAME);
            List<Publication> publicationsSetDom = domBuilder.getPublicationList();

            for (Publication publication : publicationsSetDom) {
                System.out.println(publication);
            }
            LibraryCounterService libraryCounterService = new LibraryCounterService();
            libraryCounterService.countBooks(publicationsSetDom);
            System.out.println(libraryCounterService.countGenreType(publicationsSetDom));
            System.out.println("pages = " + libraryCounterService.countAllPages(publicationsSetDom));
        // }
    }
}

