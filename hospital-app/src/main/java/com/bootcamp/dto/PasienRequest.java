package com.bootcamp.dto;

import lombok.Data;

@Data
public class PasienRequest {
    private String namaPasien;
    private String umurPasien;
    private String alamatPasien;
    private Long penyakitId;

}
