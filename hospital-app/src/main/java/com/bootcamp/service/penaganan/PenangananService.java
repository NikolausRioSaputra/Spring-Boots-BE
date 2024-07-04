package com.bootcamp.service.penaganan;

import com.bootcamp.model.Penanganan;

import java.util.List;


public interface PenangananService {
    Penanganan addPenanganan(Penanganan penanganan);

    List<Penanganan> TampilkanSemuaPasien();

    StringBuilder getNotaPenanganan(Long id);

    Penanganan validateDokterAndObat(Penanganan penanganan);

    List<Penanganan> cariPenangananBerdasarkanNamaPasien(String namaPasien);
}