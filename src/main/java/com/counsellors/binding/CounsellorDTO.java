package com.counsellors.binding;


import lombok.Data;

@Data
public class CounsellorDTO {

    private Integer counsellorId;
    private String name;
    private String email;
    private String password;
    private String phone;
}
