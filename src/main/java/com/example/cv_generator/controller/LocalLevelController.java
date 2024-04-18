package com.example.cv_generator.controller;

import com.example.cv_generator.dto.ApiResponse;
import com.example.cv_generator.dto.LocalLevelDto;
import com.example.cv_generator.service.LocalLevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/local")
public class LocalLevelController {

    private final LocalLevelService localLevelService;

    public LocalLevelController(LocalLevelService localLevelService) {
        this.localLevelService = localLevelService;
    }

    //create
    @PostMapping("/create/{district-id}")
    public ResponseEntity<LocalLevelDto> createLocalLevel(@RequestBody LocalLevelDto localLevelDto,
                                                          @PathVariable("district-id") Short districtId){
        LocalLevelDto localLevelDto1=localLevelService.createLocalLevel(localLevelDto,districtId);
        return new ResponseEntity<>(localLevelDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/update/{local-id}")
    public ResponseEntity<LocalLevelDto> updateLocal(@RequestBody LocalLevelDto localLevelDto,
                                                     @PathVariable("local-id") Short localId){
        LocalLevelDto localLevelDto1=localLevelService.updateLocalLevel(localLevelDto,localId);
        return ResponseEntity.ok(localLevelDto1);
    }

    //delete
    @DeleteMapping("/delete/{local-id}")
    public ResponseEntity<ApiResponse> deleteLocal(@PathVariable("local-id") Short localId){
        localLevelService.deleteLocalLevel(localId);
        return new ResponseEntity<>(new ApiResponse("Local Level Deleted Successfully",true),HttpStatus.OK);
    }

    //get all
    @GetMapping("/get-all")
    public ResponseEntity<List<LocalLevelDto>> getAllLocal(){
        return ResponseEntity.ok(localLevelService.getAllLocalLevel());
    }

    //get by id
    @GetMapping("/{local-id}")
    public ResponseEntity<LocalLevelDto> getLocalById(@PathVariable("local-id") Short localId){
        LocalLevelDto localLevelDto=localLevelService.getLocalDistrictById(localId);
        return new ResponseEntity<>(localLevelDto,HttpStatus.OK);
    }
}
