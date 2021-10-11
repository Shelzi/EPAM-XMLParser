package com.epam.library.processor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.epam.library.builder.DomBuilder;
import com.epam.library.entity.Publication;
import com.epam.library.validator.XmlValidator;

import org.w3c.dom.*;

public class LibraryDomParser {

    private static final String XML_FILE_NAME = "src/com/epam/library/resources/bookStructure.xml";
    private static final String XSD_FILE_NAME = "src/com/epam/library/resources/bookStructure.xsd";

    public static void main(String[] args) throws Exception {
        //if (XmlValidator.isXmlValid(XML_FILE_NAME, XSD_FILE_NAME)) {
            DomBuilder domBuilder = new DomBuilder();
            domBuilder.buildPublicationSet(XML_FILE_NAME);
            List<Publication> publicationsSetDom = domBuilder.getPublicationList();

            for (Publication publication : publicationsSetDom) {
                System.out.println(publication);
            }

            LibraryCounter.countBooks(publicationsSetDom);
            System.out.println(LibraryCounter.countGenreType(publicationsSetDom));
            System.out.println("pages = " + LibraryCounter.countAllPages(publicationsSetDom));
            //writeDocument();
        //
        // }
    }


    // Функция для сохранения DOM в файл
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("other.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}

