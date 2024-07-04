package com.bootcamp.controller;


import com.bootcamp.model.*;
import com.bootcamp.service.penaganan.PenangananService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/penanganan")
public class PenangananController {

    @Autowired
    private PenangananService penangananService;

    @GetMapping
    public HttpEntity<List<Penanganan>> tampilkanObat() {
        log.info("Mengambil semua catatan Obat");
        return ResponseEntity.ok(penangananService.TampilkanSemuaPasien());
    }

    @PostMapping
    public HttpEntity<Penanganan> tambah(@Valid  @RequestBody Penanganan penanganan) {
        Penanganan addPenaganan = penangananService.addPenanganan(penanganan);
        return new ResponseEntity<>(addPenaganan, HttpStatus.CREATED);

    }

    @GetMapping("/{id}/nota")
    public HttpEntity<StringBuilder> getNotaPenanganan(@PathVariable Long id) {
        StringBuilder nota = penangananService.getNotaPenanganan(id);
        return ResponseEntity.ok(nota);
    }

    @GetMapping("/cari")
    public HttpEntity<List<Penanganan>> cariPenangananBerdasarkanNamaPasien(@RequestParam String namaPasien) {
        List<Penanganan> penangananList = penangananService.cariPenangananBerdasarkanNamaPasien(namaPasien);
        return ResponseEntity.ok(penangananList);
    }

}