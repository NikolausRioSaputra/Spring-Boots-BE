package com.bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.IdGeneratorType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pasien")
public class Pasien {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long idPasien;

    @NotNull(message = "Nama Pasien tidak boleh kosong")
    @Size(min = 3, max = 255, message = "Nama Pasien harus memiliki panjang 3-255 karakter")
    @Column(nullable = false, length = 255)
    private String namaPasien;

    @NotNull(message = "Umur Pasien tidak boleh kosong")
    @Column(nullable = false, length = 100)
    private String umurPasien;

    @NotNull(message = "Alamat Pasien tidak boleh kosong")
    @Size(min = 1, max = 500, message = "Alamat Pasien harus memiliki panjang 10-500 karakter")
    @Column(nullable = false, length = 500)
    private String alamatPasien;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_penyakit", nullable = false)
    private Penyakit penyakit;

}