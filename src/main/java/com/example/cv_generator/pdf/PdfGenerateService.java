package com.example.cv_generator.pdf;




import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public interface PdfGenerateService {

    ByteArrayOutputStream generatePdf(Short id);

    Map<String ,String> getAllInformation(Short id) throws IOException;
}