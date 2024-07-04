package com.bootcamp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "penanganan")
public class Penanganan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPenanganan;

    @ManyToOne
    @JoinColumn(name = "idPasien")
    private Pasien pasien;

    @ManyToMany
    @JoinTable
    private List<Penyakit> penyakit;

    @ManyToOne
    @JoinColumn(name = "idDokter")
    private Dokter dokter;

    @Column(nullable = false)
    private float hargaPelayanan;

}


