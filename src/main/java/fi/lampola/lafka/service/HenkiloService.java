/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.service;

import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.googleplaces.GeocodingResponse;
import fi.lampola.lafka.repository.HenkiloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Markus
 */
@Service
public class HenkiloService {
    
    @Autowired
    HenkiloRepository henkiloRepository;
    
    @Autowired
    private GeoRestClient geoRepository;

    public Henkilo add (Henkilo henkilo, boolean setPassword) {
        String address = henkilo.getKatuosoite() + "," + henkilo.getKaupunki() + "," + henkilo.getMaa();
        address = address.replace(' ', '+');
        GeocodingResponse resp = geoRepository.findByAddress(address);
        henkilo.setLongitudi(resp.getResults().get(0).getGeometry().getLocation().getLng());
        henkilo.setLatitudi(resp.getResults().get(0).getGeometry().getLocation().getLat());
        
        if (setPassword) {
            // henkilo.setPassword(BCrypt.gensalt()); // Reset by owner later
        }
        
        return henkiloRepository.save(henkilo);
    }
}
