package com.example.cv_generator.enums;

import com.example.cv_generator.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DegreeName {
    SCHOOL(1,"SCHOOL"),
    HIGH_SCHOOL(2,"HIGH_SCHOOL"),
    BACHELOR(3,"BACHELOR"),
    MASTERS(4,"MASTERS"),
    PHD(5,"PHD");

    private Integer key;
    private String value;

    public static DegreeName findByKey(Integer key) throws NotFoundException {
        for (DegreeName status : DegreeName.values()) {
            if (status.key.equals(key)) {
                return status;
            }
        }
        throw new NotFoundException("given key not found");
    }

    public static DegreeName findByValue(String value) throws NotFoundException {
        for (DegreeName status : DegreeName.values()) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        throw new NotFoundException("given value not found");
    }
}
