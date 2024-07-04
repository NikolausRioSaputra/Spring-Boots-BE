package com.bootcamp.controller;

import com.bootcamp.exception.NoSuchAccountExistsException;
import com.bootcamp.model.Obat;
import com.bootcamp.service.obat.ObatService;
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

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ObatController.class)
@AutoConfigureMockMvc
public class ObatControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ObatService obatService;

    @InjectMocks
    private ObatController obatController;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testTambahObat_Success() throws Exception {
        // Persiapan data dummy
        Obat obat = new Obat(1L, "paracetamol", 1000);
        when(obatService.tambahObat(any(Obat.class))).thenReturn(obat);

        // Konversi obat menjadi JSON
        String obatJson = "{\"namaObat\":\"paracetamol\",\"hargaObat\":1000}";

        // Pengujian menggunakan MockMvc
        mockMvc.perform(MockMvcRequestBuilders.post("/api/hospital/obat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obatJson))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idObat").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.namaObat").value("paracetamol"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hargaObat").value(1000.0));
    }

    @Test
    public void testTambahObat_Failure() throws Exception {
        // Mock service untuk melemparkan NoSuchAccountExistsException
        when(obatService.tambahObat(any(Obat.class))).thenThrow(new NoSuchAccountExistsException("Akun tidak ditemukan"));

        String obatJson = "{\"namaObat\":\"paracetamol\",\"hargaObat\":1000}";

        // Pengujian menggunakan MockMvc
        mockMvc.perform(MockMvcRequestBuilders.post("/api/hospital/obat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(obatJson))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Akun tidak ditemukan"));
    }

    @Test
    public void testTampilkanObat() {
        Obat obat1 = new Obat(1L, "paracetamol", 1000);
        Obat obat2 = new Obat(2L, "paramex", 1000);
        Arrays Arrays = null;
        List<Obat> daftarObatYangDiharapkan = Arrays.asList(obat1, obat2);

        when(obatService.tampilkanObat()).thenReturn(daftarObatYangDiharapkan);

        List<Obat> daftarObatAktual = obatController.tampilkanObat();

        assertThat(daftarObatAktual).isEqualTo(daftarObatYangDiharapkan);
        verify(obatService, times(1)).tampilkanObat();
    }

}
