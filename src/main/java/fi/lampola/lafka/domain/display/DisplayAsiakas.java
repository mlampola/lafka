/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.domain.display;

import fi.lampola.lafka.domain.Asiakas;
import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.repository.HenkiloRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Markus
 */
public class DisplayAsiakas {
    
    private Long id;
    private String nimi;
    
    // Paikkatiedot
    private Double longitudi;
    private Double latitudi;
    
    private List<DisplayHenkilo> myyjat;

    public DisplayAsiakas(Asiakas asiakas, List<Henkilo> henkilot) {
        this.id = asiakas.getId();
        this.nimi = asiakas.getNimi();
        this.longitudi = asiakas.getLongitudi();
        this.latitudi = asiakas.getLatitudi();
        
        this.myyjat = new ArrayList();
        
        for (Henkilo h : henkilot) {
            this.myyjat.add(new DisplayHenkilo(asiakas, h));
        }
        
        this.myyjat.sort(DisplayHenkilo.EtaisyysComparator);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Double getLongitudi() {
        return longitudi;
    }

    public void setLongitudi(Double longitudi) {
        this.longitudi = longitudi;
    }

    public Double getLatitudi() {
        return latitudi;
    }

    public void setLatitudi(Double latitudi) {
        this.latitudi = latitudi;
    }

    public List<DisplayHenkilo> getMyyjat() {
        return myyjat;
    }

    public void setMyyjat(List<DisplayHenkilo> myyjat) {
        this.myyjat = myyjat;
    }
}
