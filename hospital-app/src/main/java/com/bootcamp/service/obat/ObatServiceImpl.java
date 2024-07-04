package com.bootcamp.service.obat;

import com.bootcamp.exception.NoSuchAccountExistsException;
import com.bootcamp.model.Dokter;
import com.bootcamp.model.Obat;
import com.bootcamp.repository.ObatRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
public class ObatServiceImpl implements ObatService{

    @Autowired
    private ObatRepository obatRepository;

    @Override
    public List<Obat> tampilkanObat() {
        List<Obat> daftarObat = obatRepository.findAll();
        log.info("Memuat semua data dokter dari database");
        return daftarObat;
    }

    @Override
    public Obat tambahObat(Obat obat) {
        log.info("Menambahkan dokter baru: {}", obat);
        Obat obatBaru = obatRepository.save(obat);
        return obatBaru;
    }


    @Override
    public Optional<Obat> ambilObatId(Long id) {
        log.info("Mencari obat dengan ID: {} ", id);
        Optional<Obat> pilihObat = obatRepository.findById(id);
        Obat obat = pilihObat.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Obat tidak ditemukan"));
        return Optional.ofNullable(ResponseEntity.ok(obat).getBody());
    }
}
