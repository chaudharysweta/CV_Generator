package com.example.cv_generator.service;




import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface PdfGenerateService {

    ByteArrayOutputStream generatePdf(Short id);

    String getAllInformation(Short id) throws IOException;
}