package com.bootcamp.service.penaganan;


import com.bootcamp.model.*;
import com.bootcamp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Component
public class PenangananServiceImpl implements PenangananService {

    @Autowired
    private PasienRepository pasienRepository;

    @Autowired
    private DokterRepository dokterRepository;

    @Autowired
    private ObatRepository obatRepository;

    @Autowired
    private PenangananRepository penangananRepository;

    public List<Penanganan> TampilkanSemuaPasien() {
        List<Penanganan> daftarPasien = penangananRepository.findAll();
        return daftarPasien;
    }

    public Penanganan addPenanganan(Penanganan penanganan) {
        validateDokterAndObat(penanganan);
        Penanganan penanganan1 = penangananRepository.save(penanganan);
        return penanganan1;

    }

    public Penanganan validateDokterAndObat(Penanganan penanganan) {
        Optional<Dokter> dokterOptional = dokterRepository.findById(penanganan.getDokter().getIdDokter());
        if (!dokterOptional.isPresent()) {
            throw new IllegalArgumentException("Dokter dengan ID " + penanganan.getDokter().getIdDokter() + " tidak ditemukan");
        }

        for (Penyakit penyakit : penanganan.getPenyakit()) {
            Obat obat = penyakit.getObat();
            if (obat != null) {
                Optional<Obat> obatOptional = obatRepository.findById(obat.getIdObat());
                if (obatOptional.isEmpty()) {
                    throw new IllegalArgumentException("Obat dengan ID " + obat.getIdObat() + " tidak ditemukan");
                }
            }
        }

        float totalHargaObat = 0;
        for (Penyakit penyakit : penanganan.getPenyakit()) {
            totalHargaObat += obatRepository.findById(penyakit.getObat().getIdObat()).get().getHargaObat();
        }

        float hargaPelayanan = totalHargaObat + dokterOptional.get().getTarifKonsultasi();
        penanganan.setHargaPelayanan(hargaPelayanan);

        return penanganan;
    }

    public StringBuilder getNotaPenanganan(Long id) {
        Optional<Penanganan> penangananOptional = penangananRepository.findById(id);

        if (!penangananOptional.isPresent()) {
            throw new IllegalArgumentException("Penanganan dengan ID " + id + " tidak ditemukan");
        }

        Penanganan penanganan = penangananOptional.get();

        StringBuilder nota = new StringBuilder(String.format("Nota Penanganan Pelayanan:\n" +
                        "Pasien: %s\n" +
                        "Dokter: %s\n" +
                        "Harga Pelayanan: %.1f\n",
                penanganan.getPasien().getNamaPasien(),
                penanganan.getDokter().getNamaDokter(),
                penanganan.getHargaPelayanan())
        );

        for (Penyakit penyakit : penanganan.getPenyakit()) {
            nota.append(String.format("Penyakit: %s, Obat: %s\n", penyakit.getNamaPenyakit(), penyakit.getObat().getNamaObat()));
        }
        return nota;
    }

    public List<Penanganan> cariPenangananBerdasarkanNamaPasien(String namaPasien) {
        return penangananRepository.findByPasien_NamaPasien(namaPasien);
    }
}


