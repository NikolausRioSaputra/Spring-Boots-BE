package com.bootcamp.service.impl;

import com.bootcamp.model.Dokter;
import com.bootcamp.repository.DokterRepository;
import com.bootcamp.service.docter.DokterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DokterServiceImplTest {
    @Mock
    private DokterRepository dokterRepository;

    @InjectMocks
    private DokterServiceImpl dokterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void tampilkanSemuaDokter_Success() {
        // Arrange
        List<Dokter> dokterList = new ArrayList<>();
        Dokter dokter1 = new Dokter();
        dokter1.setIdDokter(1L);
        dokter1.setNamaDokter("Dr. John Doe");
        dokter1.setSpesialisasi("Cardiologist");
        Dokter dokter2 = new Dokter();
        dokter2.setIdDokter(2L);
        dokter2.setNamaDokter("Dr. Jane Smith");
        dokter2.setSpesialisasi("Neurologist");
        dokterList.add(dokter1);
        dokterList.add(dokter2);

        when(dokterRepository.findAll()).thenReturn(dokterList);

        // Act
        List<Dokter> result = dokterService.tampilkanSemuaDokter();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Dr. John Doe", result.get(0).getNamaDokter());
        assertEquals("Cardiologist", result.get(0).getSpesialisasi());
        assertEquals("Dr. Jane Smith", result.get(1).getNamaDokter());
        assertEquals("Neurologist", result.get(1).getSpesialisasi());
    }

    @Test
    public void tambahDokter_Success() {
        // Arrange
        Dokter dokter = new Dokter();
        dokter.setNamaDokter("Dr. Adam");
        dokter.setSpesialisasi("Dermatologist");

        when(dokterRepository.save(any(Dokter.class))).thenReturn(dokter);

        // Act
        Dokter result = dokterService.tambahDokter(dokter);

        // Assert
        assertNotNull(result);
        assertEquals("Dr. Adam", result.getNamaDokter());
        assertEquals("Dermatologist", result.getSpesialisasi());
    }

    @Test
    public void dokterDariId_ValidId_Success() {
        // Arrange
        Long id = 1L;
        Dokter dokter = new Dokter();
        dokter.setIdDokter(id);
        dokter.setNamaDokter("Dr. Michael");
        dokter.setSpesialisasi("Pediatrician");

        when(dokterRepository.findById(id)).thenReturn(Optional.of(dokter));

        // Act
        Optional<Dokter> result = dokterService.dokterDariId(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Dr. Michael", result.get().getNamaDokter());
        assertEquals("Pediatrician", result.get().getSpesialisasi());
    }

    @Test
    public void dokterDariId_InvalidId_NotFoundException() {
        // Arrange
        Long id = 999L; // ID yang tidak ada dalam repository

        when(dokterRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> {
            dokterService.dokterDariId(id);
        });
    }
}
