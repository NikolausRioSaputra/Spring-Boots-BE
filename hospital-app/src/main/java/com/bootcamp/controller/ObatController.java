package com.bootcamp.controller;

import com.bootcamp.exception.AccountAlreadyExistsException;
import com.bootcamp.exception.NoSuchAccountExistsException;
import com.bootcamp.model.Obat;
import com.bootcamp.service.obat.ObatService;
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
@RequestMapping("/api/hospital/obat")
public class ObatController {

    @Autowired
    private ObatService obatService;


    @GetMapping
    public List<Obat> tampilkanObat() {
        log.info("Mengambil semua catatan Obat");
        return obatService.tampilkanObat();
    }

    @GetMapping("/{id}")
    public HttpEntity<Optional<Obat>> obatDariId(@PathVariable("id") Long id) {
        log.info("Mengambil Obat dengan ID", id);
        Optional<Obat> obat = obatService.ambilObatId(id);
        if(obat.isPresent()){
            return new ResponseEntity<>(Optional.of(obat.get()), HttpStatus.OK);
        }else {
            log.warn("Obat dengan ID {} tidak di temukan", id);
            throw new NoSuchAccountExistsException("Obat tidak di temukan dengan ID: " + id);
        }
    }

    @PostMapping
    public HttpEntity<?> tambahkan(@Valid @RequestBody Obat obat) {
        log.info("Menambahkan obat baru: {}", obat);
        try {
            Obat addObat = obatService.tambahObat(obat);
            log.info("Obat baru berhasil ditambahkan dengan Id: {}", addObat.getIdObat());
            return new ResponseEntity<>(addObat, HttpStatus.CREATED);
        } catch (NoSuchAccountExistsException e){
            log.error("Gagal menambahkan obat: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
