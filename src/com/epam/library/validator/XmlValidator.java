package com.epam.library.validator;
import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import com.epam.library.handler.
        StudentsErrorHandler;
import org.xml.sax.SAXException;

public class XmlValidator {
        public static boolean isXmlValid(String xmlFileName, String xmlSchemaName) {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            File schemaLocation = new File(xmlSchemaName);
            try {
                // schema creation
                Schema schema = factory.newSchema(schemaLocation);
                // creating a schema-based validator
                Validator validator = schema.newValidator();
                Source source = new StreamSource(xmlFileName);
                // document check
                validator.setErrorHandler(new StudentsErrorHandler());
                validator.validate(source);
            } catch (SAXException | IOException e) {
                System.err.println(xmlFileName + " is not correct or valid   " + e );
            }
            return true;
        }
}
