/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Markus
 */
@Entity
public class Asiakas extends AbstractPersistable<Long>{

    private String nimi;
    
    private String katuosoite; // Esim. Helsinginkatu 20 (ilman rappua/huoneistoa)
    private String huoneisto; // Esim. A 1
    private String kaupunki;
    private String maa;

    private Integer myyntiarvio; // Arvioitu myyntimäärä (kpl)
    
    // Paikkatiedot
    private Double longitudi;
    private Double latitudi;
    
    @OneToMany(mappedBy="asiakas")
    private List<Tehtava> asiakkaat;

    public Asiakas() {
        this.setMaa("Finland");
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }

    public String getHuoneisto() {
        return huoneisto;
    }

    public void setHuoneisto(String huoneisto) {
        this.huoneisto = huoneisto;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    public Integer getMyyntiarvio() {
        return myyntiarvio;
    }

    public void setMyyntiarvio(Integer myyntiarvio) {
        this.myyntiarvio = myyntiarvio;
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

    public List<Tehtava> getAsiakkaat() {
        return asiakkaat;
    }

    public void setAsiakkaat(List<Tehtava> asiakkaat) {
        this.asiakkaat = asiakkaat;
    }

    public String getMaa() {
        return maa;
    }

    public void setMaa(String maa) {
        this.maa = maa;
    }
}
