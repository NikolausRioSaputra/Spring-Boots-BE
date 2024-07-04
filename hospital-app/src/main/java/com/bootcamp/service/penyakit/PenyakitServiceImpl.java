package com.bootcamp.service.penyakit;

import com.bootcamp.dto.PenyakitRequest;
import com.bootcamp.exception.NoSuchAccountExistsException;
import com.bootcamp.model.Obat;
import com.bootcamp.model.Penyakit;
import com.bootcamp.repository.ObatRepository;
import com.bootcamp.repository.PenyakitRepository;
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
public class PenyakitServiceImpl implements PenyakitService {

    @Autowired
    private PenyakitRepository penyakitRepository;

    @Autowired
    private ObatRepository obatRepository;


    @Override
    public List<Penyakit> tampilkanSemuaPenyakit() {
        List<Penyakit> daftarPenyakit = penyakitRepository.findAll();
        log.info("Memuat semua data penyakit dari database");
        return ResponseEntity.status(HttpStatus.CREATED).body(daftarPenyakit).getBody();
    }

    @Override
    public Penyakit tambahPenyakit(PenyakitRequest request) {
        Obat obat = obatRepository.findById(request.getObatId())
                .orElseThrow(() -> new NoSuchAccountExistsException("Obat dengan ID " + request.getObatId() + " tidak ditemukan"));
        Penyakit penyakit = new Penyakit();
        penyakit.setNamaPenyakit(request.getNamaPenyakit());
        penyakit.setObat(obat);
        return penyakitRepository.save(penyakit);
    }

    @Override
    public Optional<Penyakit> penyakitDariId(Long id) {
        log.info("Mencari penyakit dengan ID: {}", id);
        Optional<Penyakit> pilihPenyakit = penyakitRepository.findById(id);
        Penyakit penyakit = pilihPenyakit.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Penyakit tidak ditemukan"));
        return Optional.ofNullable(ResponseEntity.ok(penyakit).getBody());
    }


//    @Override
//    public Penyakit updatePenyakitId(Long id, PenyakitRequest request) {
//        Penyakit penyakit = penyakitRepository.findById(id)
//                .orElseThrow(() -> new NoSuchAccountExistsException("Penyakit dengan ID " + id + " tidak ditemukan"));
//        Obat obat = obatRepository.findById(request.getObatId())
//                .orElseThrow(() -> new NoSuchAccountExistsException("Obat dengan ID " + request.getObatId() + " tidak ditemukan"));
//        penyakit.setNamaPenyakit(request.getNamaPenyakit());
//        penyakit.setObat(obat);
//        return penyakitRepository.save(penyakit);
//    }
//
//    @Override
//    public void hapus(Long id) {
//        if (!penyakitRepository.existsById(id)) {
//            throw new NoSuchAccountExistsException("Penyakit dengan ID " + id + " tidak ditemukan");
//        }
//        penyakitRepository.deleteById(id);
//    }



}
