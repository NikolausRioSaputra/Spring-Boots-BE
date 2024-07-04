package com.bootcamp.controller;


import com.bootcamp.dto.PasienRequest;
import com.bootcamp.exception.NoSuchAccountExistsException;
import com.bootcamp.model.Pasien;
import com.bootcamp.model.Penyakit;
import com.bootcamp.service.pasien.PasienService;
import com.bootcamp.service.penyakit.PenyakitService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api/hospital/pasien")
public class PasienController {

    @Autowired
    private PasienService pasienService;

    @Autowired
    private PenyakitService penyakitService;

    @GetMapping
    public List<Pasien> tampilkanSemuaPasien() {
        log.info("Mengambil semua catatan Pasien");
        return pasienService.TampilkanSemuaPasien();
    }

    @GetMapping("/{id}")
    public HttpEntity<Pasien> pasienDariId(@PathVariable("id") Long id) {
        log.info("Mengambil Pasien dengan ID {}", id);
        Pasien pasien = pasienService.pasienDariId(id);
        return new ResponseEntity<>(pasien, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Pasien> tambahkan(@Valid @RequestBody PasienRequest pasienRequest) {
        log.info("Menambahkan pasien baru: {}", pasienRequest);

        Penyakit penyakit = penyakitService.penyakitDariId(pasienRequest.getPenyakitId())
                .orElseThrow(() -> new NoSuchAccountExistsException("Penyakit dengan ID " + pasienRequest.getPenyakitId() + " tidak ditemukan"));

        Pasien pasien = new Pasien();
        pasien.setNamaPasien(pasienRequest.getNamaPasien());
        pasien.setUmurPasien(pasienRequest.getUmurPasien());
        pasien.setAlamatPasien(pasienRequest.getAlamatPasien());
        pasien.setPenyakit(penyakit);

        Pasien addPasien = pasienService.tambahPasien(pasien);
        return new ResponseEntity<>(addPasien, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public HttpEntity<Pasien> replace(@PathVariable("id") Long id, @Valid @RequestBody PasienRequest pasienRequest) {
//        log.info("Mengganti Pasien dengan Id {}: {}", id, pasienRequest);
//
//        Penyakit penyakit = penyakitService.penyakitDariId(pasienRequest.getPenyakitId())
//                .orElseThrow(() -> new NoSuchAccountExistsException("Penyakit dengan ID " + pasienRequest.getPenyakitId() + " tidak ditemukan"));
//
//        Pasien existingPasien = pasienService.pasienDariId(id)
//                .orElseThrow(() -> new NoSuchAccountExistsException("Pasien dengan ID " + id + " tidak ditemukan"));
//
//        existingPasien.setNamaPasien(pasienRequest.getNamaPasien());
//        existingPasien.setUmurPasien(pasienRequest.getUmurPasien());
//        existingPasien.setAlamatPasien(pasienRequest.getAlamatPasien());
//        existingPasien.setPenyakit(penyakit);
//
//        Pasien updatedPasien = pasienService.updatePasienId(id, existingPasien);
//        return new ResponseEntity<>(updatedPasien, HttpStatus.OK);
//    }

//    @DeleteMapping("/{id}")
//    public HttpEntity<Void> hapus(@PathVariable("id") Long id) {
//        try {
//            log.info("Menghapus Pasien dengan Id: {}", id);
//            pasienService.hapus(id);
//            return ResponseEntity.noContent().build();
//        } catch (NoSuchAccountExistsException ex) {
//            log.error("Pasien dengan id {} tidak ditemukan", id, ex);
//            return ResponseEntity.notFound().build();
//        } catch (Exception ex) {
//            log.error("Gagal menghapus Pasien dengan id {}", id, ex);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
