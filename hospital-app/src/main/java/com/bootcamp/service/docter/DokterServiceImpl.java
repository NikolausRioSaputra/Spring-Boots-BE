package com.bootcamp.service.docter;

import com.bootcamp.model.Dokter;
import com.bootcamp.repository.DokterRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@Component
public class DokterServiceImpl implements DokterService {

    @Autowired
    private DokterRepository dokterRepository;

    @Override
    public List<Dokter> tampilkanSemuaDokter() {
        List<Dokter> daftarDokter = dokterRepository.findAll();
        log.info("Memuat semua data dokter dari database");
        return daftarDokter;
    }

    @Override
    public Dokter tambahDokter(Dokter dokter) {
        log.info("Menambahkan dokter baru: {}", dokter);
        Dokter dokterBaru = dokterRepository.save(dokter);
        return dokterBaru;
    }

    @Override
    public Optional<Dokter> dokterDariId(Long id) {
        log.info("Mencari dokter dengan ID: {}", id);
        Optional<Dokter> pilihDokter = dokterRepository.findById(id);
        Dokter dokter = pilihDokter.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dokter tidak ditemukan"));
        return Optional.ofNullable(ResponseEntity.ok(dokter).getBody());
    }
}
