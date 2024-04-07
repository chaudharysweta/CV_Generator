package com.example.cv_generator.enums;

import com.example.cv_generator.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ProjectStatus {

    LIVE(1,"LIVE"),
    UAT(2,"UAT"),
    DEVELOPMENT(3,"DEVELOPMENT");

    private Integer key;
    private String value;


    public static ProjectStatus findByKey(Integer key) throws NotFoundException{
        for (ProjectStatus status:ProjectStatus.values()){
            if (status.key.equals(key)){
                return status;
            }
        }
        throw new NotFoundException("Given Key Not Found");
    }

    public static ProjectStatus findByValue(String value) throws NotFoundException{
        for (ProjectStatus status:ProjectStatus.values()){
            if (status.value.equals(value)){
                return status;
            }
        }
        throw new NotFoundException("Given Value Not Found");
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
