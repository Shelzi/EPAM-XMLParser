package com.epam.library.handler;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;
public class StudentsErrorHandler implements ErrorHandler {

    public void warning(SAXParseException e) {
        System.out.println(getLineColumnNumber(e) + "-" + e.getMessage());
    }
    public void error(SAXParseException e) {
        System.out.println(getLineColumnNumber(e) + "-" + e.getMessage());
    }
    public void fatalError(SAXParseException e) {
        System.out.println(getLineColumnNumber(e) + "-" + e.getMessage());
    }
    private String getLineColumnNumber(SAXParseException e) {
        // determine line and position of error
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}