package com.example.cv_generator.controller;

import com.example.cv_generator.config.MessageCodeConstant;
import com.example.cv_generator.config.MessageConstant;
import com.example.cv_generator.dto.BasicInformationDto;
import com.example.cv_generator.dto.CustomMessageSource;
import com.example.cv_generator.dto.GlobalApiResponse;
import com.example.cv_generator.entity.BasicInformation;
import com.example.cv_generator.repository.BasicInformationRepository;
import com.example.cv_generator.service.BasicInformationService;
import com.example.cv_generator.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/basic-info")
public class BasicInformationController extends BaseController{

    private final BasicInformationService basicInformationService;

    private final CustomMessageSource customMessageSource;

    private final BasicInformationRepository basicInformationRepository;

    private final FileService fileService;

    @Value("${project.image}")
    private String path;




    public BasicInformationController(BasicInformationService basicInformationService, CustomMessageSource customMessageSource, FileService fileService, BasicInformationRepository basicInformationRepository, FileService fileService1) throws IOException {
        this.basicInformationService = basicInformationService;
        this.customMessageSource = customMessageSource;
        this.basicInformationRepository = basicInformationRepository;
        this.fileService = fileService1;
        this.messageCode= MessageCodeConstant.BASIC_INFORMATION;
    }


    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createBasicInformation(@Valid @RequestBody BasicInformationDto basicInformationDto){
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_CREATE,customMessageSource.
                get(messageCode)),basicInformationService.createBasicInformation(basicInformationDto)),HttpStatus.OK);
    }

    // update

    @PutMapping("/{basic-info-id}")
    public ResponseEntity<GlobalApiResponse> updateBasicInfo(@Valid @RequestBody BasicInformationDto basicInformationDto,
                                                             @PathVariable("basic-info-id") Short basicInfoId) {
        basicInformationService.updateBasicInformation(basicInformationDto, basicInfoId);

        return  ResponseEntity.ok(successResponse(customMessageSource.get(MessageConstant.CRUD_UPDATE, customMessageSource
                .get(MessageCodeConstant.BASIC_INFORMATION)),null));
    }
    @DeleteMapping("/delete/{basic-info-id}")
    public ResponseEntity<GlobalApiResponse> deleteBasicInformation(@PathVariable("basic-info-id") Short basicInfoId){
       if (basicInfoId==null){
           throw new NullPointerException("Id is null");
       }
       basicInformationService.deleteBasicInformation(basicInfoId);
       return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_DELETE,customMessageSource
                       .get(MessageCodeConstant.BASIC_INFORMATION)),"Id:" +basicInfoId),HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<GlobalApiResponse>getAllBasicInfo(){
        return new  ResponseEntity<>(successResponse(customMessageSource.get
                (MessageConstant.CRUD_GET_ALL,customMessageSource.get(messageCode)),
                basicInformationService.getAllBasicInformation()),HttpStatus.OK);

    }

    @GetMapping("/find/{basic-info-id}")
    public ResponseEntity<GlobalApiResponse> getSingleBasicInfo(@PathVariable("basic-info-id") Short basicInfoId){

        if (basicInfoId==null){
            throw new NullPointerException("Id is null");
        }
        return new ResponseEntity<>(successResponse(customMessageSource.get(MessageConstant.CRUD_GET,customMessageSource.
                get(MessageCodeConstant.BASIC_INFORMATION)),basicInformationService.getBasicInformationById(basicInfoId)),HttpStatus.OK);
    }


    @PostMapping("/image/upload/{id}")
    public ResponseEntity<BasicInformationDto> uploadProfileImage(@RequestParam("image") MultipartFile image, @PathVariable Short id
    ) throws IOException {

        BasicInformationDto basicInformationDto = basicInformationService.getBasicInformationById(id);

        String fileName = fileService.uploadImage(path, image);

        basicInformationDto.setProfileImage(fileName);
        BasicInformationDto updateBasicInfo = basicInformationService.updateBasicInformation(basicInformationDto, id);
        return new ResponseEntity<>(updateBasicInfo,HttpStatus.OK);
    }

    // method to serve file
    @GetMapping(value = "/image/{image-name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("image-name") String imageName, HttpServletResponse response
    ) throws IOException {

        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }


}
