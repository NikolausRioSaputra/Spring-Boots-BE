package com.bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "penyakit")
public class Penyakit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPenyakit;

    @NotNull(message = "Nama Penyakit tidak boleh kosong")
    @Size(min = 3, max = 255, message = "Nama Penyakit harus memiliki panjang 3-255 karakter")
    @Column(nullable = false, length = 225)
    private String namaPenyakit;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "obat_id", referencedColumnName = "idObat")
    private Obat obat;



}
