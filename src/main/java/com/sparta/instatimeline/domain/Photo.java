package com.sparta.instatimeline.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    private String fileName;
    private String fileExtension;

    public String photoFileName() {
        return fileName + "." + fileExtension;
    }

}
