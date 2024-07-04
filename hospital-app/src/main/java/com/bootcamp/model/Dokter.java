package com.bootcamp.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dokter")
public class Dokter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDokter;

    @NotNull(message = "Spesialisasi tidak boleh kosong")
    @Size(min = 3, max = 255, message = "Spesialisasi harus memiliki panjang 3-255 karakter")
    @Column(nullable = false, length = 225)
    private String namaDokter;

    @NotNull(message = "Nama Spesialis tidak boleh kosong")
    @Size(min = 3, max = 255, message = "Nama Spesialis harus memiliki panjang 3-255 karakter")
    @Column( nullable = false, length = 225)
    private String spesialisasi;

    @NotNull(message = "Tarif Konsultasi tidak boleh kosong")
    @Column(nullable = false)
    private float tarifKonsultasi;

}

