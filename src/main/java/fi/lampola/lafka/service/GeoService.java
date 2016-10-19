package fi.lampola.lafka.service;

import fi.lampola.lafka.googleplaces.GeocodingResponse;

public interface GeoService {

    void setUri(String uri);

    GeocodingResponse findByAddress(String address);
}
