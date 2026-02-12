package com.araujo.araujofood.domain.service;

import com.araujo.araujofood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

}
