package com.bootcamp.service.pasien;

import com.bootcamp.model.Pasien;

import java.util.List;
import java.util.Optional;

public interface PasienService {
    List<Pasien> TampilkanSemuaPasien();

    Pasien tambahPasien(Pasien pasien);

    Pasien pasienDariId(Long id);

//    Pasien updatePasienId(Long id, Pasien existingPasien);
//
//    void hapus(Long id);
}
