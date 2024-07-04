package com.bootcamp.service.pasien;

import com.bootcamp.exception.NoSuchAccountExistsException;
import com.bootcamp.model.Dokter;
import com.bootcamp.model.Pasien;
import com.bootcamp.repository.PasienRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PasienServiceImpl implements PasienService {


    @Autowired
    private PasienRepository pasienRepository;

    @Override
    public List<Pasien> TampilkanSemuaPasien() {
        List<Pasien> daftarPasien = pasienRepository.findAll();
        log.info("Memuat semua data dari Pasien");
        return daftarPasien;
    }

    @Override
    public Pasien tambahPasien(Pasien pasien) {
        log.info("Menambahkan pasien baru: {}", pasien);
        Pasien pasienBaru = pasienRepository.save(pasien);
        return pasienBaru;
    }

    @Override
    public Pasien pasienDariId(Long id) {
        log.info("Mencari dokter dengan ID: {}", id);
        Optional<Pasien> pilihPasien = pasienRepository.findById(id);
        Pasien pasien = pilihPasien.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pasien tidak ditemukan"));
        return pasien;
    }

//    @Override
//    public Pasien updatePasienId(Long id, Pasien pasien) {
//        Optional<Pasien> existingPasienOpt = pasienRepository.findById(id);
//        if (existingPasienOpt.isPresent()) {
//            Pasien existingPasien = existingPasienOpt.get();
//            existingPasien.setNamaPasien(pasien.getNamaPasien());
//            existingPasien.setUmurPasien(pasien.getUmurPasien());
//            existingPasien.setAlamatPasien(pasien.getAlamatPasien());
//            existingPasien.setPenyakit(pasien.getPenyakit());
//            return pasienRepository.save(existingPasien);
//        } else {
//            throw new NoSuchAccountExistsException("Pasien dengan ID " + id + " tidak ditemukan");
//        }
//    }
//
//    @Override
//    public void hapus(Long id) {
//        if (pasienRepository.existsById(id)) {
//            pasienRepository.deleteById(id);
//        } else {
//            throw new NoSuchAccountExistsException("Pasien dengan ID " + id + " tidak ditemukan");
//        }
//    }
}
