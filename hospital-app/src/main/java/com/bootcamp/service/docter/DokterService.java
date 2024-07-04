package com.bootcamp.service.docter;

import com.bootcamp.model.Dokter;

import java.util.List;
import java.util.Optional;

public interface DokterService {
    List<Dokter> tampilkanSemuaDokter();

    Dokter tambahDokter(Dokter dokter);

    Optional<Dokter> dokterDariId(Long id);
}
