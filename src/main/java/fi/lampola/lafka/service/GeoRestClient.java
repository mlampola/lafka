/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import fi.lampola.lafka.googleplaces.GeocodingResponse;

/**
 *
 * @author Markus
 */
@Service
public class GeoRestClient implements GeoService {

    private RestTemplate restTemplate;
    private String uri;

    public GeoRestClient() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public GeocodingResponse findByAddress(String address) {
        return restTemplate.getForObject(uri + "/{address}", GeocodingResponse.class, address);
    }
}
