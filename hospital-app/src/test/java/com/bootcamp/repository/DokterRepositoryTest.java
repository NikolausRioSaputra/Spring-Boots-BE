package com.bootcamp.repository;

import com.bootcamp.model.Dokter;

import com.bootcamp.service.docter.DokterService;
import com.bootcamp.service.docter.DokterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DokterRepositoryTest {

    @Mock
    private DokterRepository dokterRepository;

    @InjectMocks
    private DokterService dokterService = new DokterServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTambahDokter() {
        // Arrange
        Dokter dokter = new Dokter();
        dokter.setIdDokter(1L);
        dokter.setNamaDokter("Dr. John Doe");
        dokter.setSpesialisasi("Cardiologist");

        when(dokterRepository.save(any(Dokter.class))).thenReturn(dokter);

        // Act
        Dokter result = dokterService.tambahDokter(dokter);

        // Assert
        assertEquals("Dr. John Doe", result.getNamaDokter());
        assertEquals("Cardiologist", result.getSpesialisasi());
        assertEquals(1L, result.getIdDokter());
    }

    @Test
    public void testDokterDariId() {
        // Arrange
        Long dokterId = 1L;
        Dokter dokter = new Dokter();
        dokter.setIdDokter(dokterId);
        dokter.setNamaDokter("Dr. Jane Smith");
        dokter.setSpesialisasi("Neurologist");

        when(dokterRepository.findById(dokterId)).thenReturn(Optional.of(dokter));

        // Act
        Optional<Dokter> result = dokterService.dokterDariId(dokterId);

        // Assert
        assertEquals(dokterId, result.get().getIdDokter());
        assertEquals("Dr. Jane Smith", result.get().getNamaDokter());
        assertEquals("Neurologist", result.get().getSpesialisasi());
    }
}
