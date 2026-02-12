package com.araujo.araujofood.domain.service;

import com.araujo.araujofood.domain.filter.VendaDiariaFilter;
import com.araujo.araujofood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter vendaDiariaFilter, String timeOffset);

}
