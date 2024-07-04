package com.bootcamp.controller;

import com.bootcamp.exception.NoSuchAccountExistsException;
import com.bootcamp.model.Dokter;
import com.bootcamp.service.docter.DokterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(DokterController.class)
@AutoConfigureMockMvc
public class DokterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DokterService dokterService;

    @InjectMocks
    private DokterController dokterController;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testTambahDokter_Success() throws Exception {
        Dokter dokter = new Dokter(1L, "Dr. niko ganteng", "umum", 10000);
        when(dokterService.tambahDokter(any(Dokter.class))).thenReturn(dokter);

        String dokterJson = "{\n" +
                "    \"idDokter\": 1,\n" +
                "    \"namaDokter\": \"Dr. niko ganteng\",\n" +
                "    \"spesialisasi\": \"umum\",\n" +
                "    \"tarifKonsultasi\": 10000.0\n" +
                "}";

        mockMvc.perform(post("/api/hospital/dokter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dokterJson)
                        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idDokter").value(1))
                .andExpect(jsonPath("$.namaDokter").value("Dr. niko ganteng"))
                .andExpect(jsonPath("$.spesialisasi").value("umum"))
                .andExpect(jsonPath("$.tarifKonsultasi").value(10000.0));
    }

    @Test
    public void testTambahDokter_Failure() throws Exception {
        when(dokterService.tambahDokter(any(Dokter.class))).thenThrow(new NoSuchAccountExistsException("Akun tidak ditemukan"));

        String dokterJson = "{\"idDokter\":1,\"namaDokter\":\"Dr. John Doe\",\"spesialisasi\":\"Cardiologist\",\"tarifKonsultasi\":20000.0}";

        mockMvc.perform(post("/api/hospital/dokter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dokterJson)
                        .with(csrf()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Akun tidak ditemukan"));
    }


    @Test
    public void testTampilkanDokter() {
        // Arrange (Pengaturan)
        Dokter dokter1 = new Dokter(1L, "Dr. A", "penyakit gok", 121212);
        Dokter dokter2 = new Dokter(2L, "Dr. B", "penyakit waw", 23456);
        Arrays Arrays = null;
        List<Dokter> daftarDokterYangDiharapkan = Arrays.asList(dokter1, dokter2);

        when(dokterService.tampilkanSemuaDokter()).thenReturn(daftarDokterYangDiharapkan);

        List<Dokter> daftarDokterAktual = dokterController.tampilkanDokter();

        // Assert (Verifikasi)
        assertThat(daftarDokterAktual).isEqualTo(daftarDokterYangDiharapkan);
        verify(dokterService, times(1)).tampilkanSemuaDokter();
    }

}