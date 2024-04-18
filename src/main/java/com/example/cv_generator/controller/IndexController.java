package com.example.cv_generator.controller;

import com.example.cv_generator.service.PdfGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    @Autowired
    private PdfGenerateService pdfGenerateService;

    @GetMapping("/template")
    public String openTemplate() {
        return "index";
    }

    @GetMapping("/generatePdf/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Short id) {
        byte[] pdf = pdfGenerateService.generatePdf(id).toByteArray();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        header.setContentLength(pdf.length);
        header.set("Content-Disposition", "attachment; filename=" + "pdf-" + id + ".pdf");
        return new ResponseEntity<>(pdf, header, HttpStatus.OK);
    }

    @GetMapping("/generateHtml/{id}")
    public ResponseEntity<String> generateHtml(@PathVariable Short id) throws IOException {

        return  ResponseEntity.ok(pdfGenerateService.getAllInformation(id));
    }
}