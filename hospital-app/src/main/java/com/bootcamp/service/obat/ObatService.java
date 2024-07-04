package com.bootcamp.service.obat;

import com.bootcamp.model.Obat;
import java.util.List;
import java.util.Optional;

public interface ObatService {

    List<Obat> tampilkanObat();

    Obat tambahObat(Obat obat);

    Optional<Obat> ambilObatId(Long id);
}
