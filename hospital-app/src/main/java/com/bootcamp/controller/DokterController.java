package com.bootcamp.controller;

import com.bootcamp.exception.NoSuchAccountExistsException;
import com.bootcamp.model.Dokter;
import com.bootcamp.service.docter.DokterService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/hospital/dokter")
public class DokterController {

    @Autowired
    private DokterService dokterService;

    @GetMapping
    public List<Dokter> tampilkanDokter() {
        log.info("Mengambil semua Catatan Dokter");
        return dokterService.tampilkanSemuaDokter();
    }

    @GetMapping("/{id}")
    public HttpEntity<Dokter> tampilkanDokterId(@PathVariable("id") Long id) {
        log.info("Mengambil dokter dengan ID: {}", id);
        Optional<Dokter> dokter = dokterService.dokterDariId(id);
        if (dokter.isPresent()) {
            return new ResponseEntity<>(dokter.get(), HttpStatus.OK);
        } else {
            log.warn("Dokter dengan ID {} tidak ditemukan", id);
            throw new NoSuchAccountExistsException("Dokter tidak ditemukan dengan ID: " + id);
        }
    }

    @PostMapping
    public HttpEntity<?> tambahakanDokterbaru(@Valid @RequestBody Dokter dokter) {
        log.info("Menambahkan dokter baru: {}", dokter);
        try {
            Dokter addDokter = dokterService.tambahDokter(dokter);
            log.info("Dokter baru berhasil ditambahkan dengan ID: {}", addDokter.getIdDokter());
            return new ResponseEntity<>(addDokter, HttpStatus.CREATED);
        } catch (NoSuchAccountExistsException e) {
            log.error("Gagal menambahkan dokter: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
