package com.bootcamp.controller;

import com.bootcamp.dto.PenyakitRequest;
import com.bootcamp.exception.AccountAlreadyExistsException;
import com.bootcamp.exception.NoSuchAccountExistsException;
import com.bootcamp.model.Penyakit;
import com.bootcamp.service.penyakit.PenyakitService;
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
@RequestMapping("/api/hospital/penyakit")
public class PenyakitController {

    @Autowired
    private PenyakitService penyakitService;

    @GetMapping
    public List<Penyakit> tampilkanPenyakit() {
        log.info("Mengambil semua Catatan Penyakit");
        return penyakitService.tampilkanSemuaPenyakit();
    }

    @GetMapping("/{id}")
    public HttpEntity<Optional<Penyakit>> penyakitDariId(@PathVariable("id") Long id) {
        log.info("Mengambil Penyakit dengan ID", id);
        Optional<Penyakit> penyakit = penyakitService.penyakitDariId(id);
        if(penyakit.isPresent()){
            return new ResponseEntity<>(Optional.of(penyakit.get()), HttpStatus.OK);
        }else {
            log.warn("Penyakit dengan ID {} tidak di temukan", id);
            throw new NoSuchAccountExistsException("Penyakit tidak ditemukan dengan ID: " + id);
        }
    }

    @PostMapping
    public HttpEntity<Penyakit> tambahkan(@Valid @RequestBody PenyakitRequest request) {
        log.info("Menambahkan penyakit baru: {}", request);
        try {
            Penyakit penyakit = penyakitService.tambahPenyakit(request);
            log.info("Penyakit baru berhasil ditambahkan dengan Id: {}", penyakit.getIdPenyakit());
            return new ResponseEntity<>(penyakit, HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Gagal menambahkan penyakit: {}", e.getMessage());
            throw new AccountAlreadyExistsException("Gagal menambahkan penyakit: " + e.getMessage(), e);
        }
    }

//    @PutMapping("/{id}")
//    public HttpEntity<Penyakit> ganti(@PathVariable("id") Long id, @RequestBody PenyakitRequest request, BindingResult result) {
//        if (result.hasErrors()) {
//            throw new IllegalArgumentException("Object Penyakit tidak valid");
//        }
//        log.info("Mengganti Penyakit dengan Id {}: {}", id, request);
//        Penyakit penyakit = penyakitService.updatePenyakitId(id, request);
//        return new ResponseEntity<>(penyakit, HttpStatus.OK);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public HttpEntity<Penyakit> hapus(@PathVariable("id") Long id){
//        try {
//            log.info("Menghapus Penyakit dengan Id: {}", id);
//            penyakitService.hapus(id);
//            return ResponseEntity.noContent().build();
//        } catch (NoSuchAccountExistsException ex) {
//            log.error("Penyakit dengan id {} tidak ditemukan", id, ex);
//            return ResponseEntity.notFound().build();
//        } catch (Exception ex) {
//            log.error("Gagal menghapus Penyakit dengan id {}", id, ex);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }



}
