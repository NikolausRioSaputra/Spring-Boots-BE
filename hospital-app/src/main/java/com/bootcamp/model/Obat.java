package com.bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "obat")
public class Obat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObat;

    @NotNull(message = "Nama Obat tidak boleh kosong")
    @Size(min = 3, max = 255, message = "Nama Obat harus memiliki panjang 3-255 karakter")
    @Column(nullable = false, length = 255)
    private String namaObat;

    @NotNull(message = "Harga Obat tidak boleh kosong")
    @Column(nullable = false)
    private float hargaObat;

}
