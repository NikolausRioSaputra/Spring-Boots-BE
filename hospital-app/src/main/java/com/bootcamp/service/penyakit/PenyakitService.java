package com.bootcamp.service.penyakit;

import com.bootcamp.dto.PenyakitRequest;
import com.bootcamp.model.Penyakit;

import java.util.List;
import java.util.Optional;

public interface PenyakitService {

    List<Penyakit> tampilkanSemuaPenyakit();

    Penyakit tambahPenyakit(PenyakitRequest request);

    Optional<Penyakit> penyakitDariId(Long id);
//
//    void hapus(Long id);
//
//    Penyakit updatePenyakitId(Long id, PenyakitRequest request);


}
