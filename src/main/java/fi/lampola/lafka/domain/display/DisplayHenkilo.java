/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.domain.display;

import fi.lampola.lafka.domain.Asiakas;
import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.util.Etaisyys;

/**
 *
 * @author Markus
 */
public class DisplayHenkilo {
    
    private Long id;
    private String nimi;
    private int tehtavaLkm;
    private double etaisyys; // km
    private String ilmansuunta;
    
    // Paikkatiedot
    private Double longitudi;
    private Double latitudi;

    public DisplayHenkilo(Asiakas asiakas, Henkilo henkilo) {
        this.id = henkilo.getId();
        this.nimi = henkilo.getSukunimi() + ", " + henkilo.getEtunimi();
        this.tehtavaLkm = henkilo.getTehtavat().size();        
        this.longitudi = henkilo.getLongitudi();
        this.latitudi = henkilo.getLatitudi();
        
        this.etaisyys = Etaisyys.laskeEtaisyys(
                asiakas.getLongitudi(), 
                asiakas.getLatitudi(), 
                henkilo.getLongitudi(),
                henkilo.getLatitudi());
        
        this.ilmansuunta = Etaisyys.laskeSuunta( // Asiakkaasta katsoen
                asiakas.getLongitudi(), 
                asiakas.getLatitudi(), 
                henkilo.getLongitudi(),
                henkilo.getLatitudi());
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

    public int getTehtavaLkm() {
        return tehtavaLkm;
    }

    public void setTehtavaLkm(int tehtavaLkm) {
        this.tehtavaLkm = tehtavaLkm;
    }

    public double getEtaisyys() {
        return etaisyys;
    }

    public void setEtaisyys(double etaisyys) {
        this.etaisyys = etaisyys;
    }

    public String getIlmansuunta() {
        return ilmansuunta;
    }

    public void setIlmansuunta(String ilmansuunta) {
        this.ilmansuunta = ilmansuunta;
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
}
