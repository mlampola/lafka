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
public class Henkilo extends AbstractPersistable<Long>{

    private String nimi;
    
    @Column(unique = true)
    private String username;
    
    private String password;

    private String katuosoite; // Esim. Helsinginkatu 20 (ilman rappua/huoneistoa)
    private String huoneisto; // Esim. A 1
    private String kaupunki;
    private String maa;
    
    @Column(unique = true)
    private String email;
    
    @OneToMany(mappedBy="myyja")
    private List<Tehtava> tehtavat;
    
    // Paikkatiedot
    private Double longitudi;
    private Double latitudi;

    public Henkilo() {
        this.setMaa("Finland");
    }
    
    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Tehtava> getTehtavat() {
        return tehtavat;
    }

    public void setTehtavat(List<Tehtava> tehtavat) {
        this.tehtavat = tehtavat;
    }

    public String getMaa() {
        return maa;
    }

    public void setMaa(String maa) {
        this.maa = maa;
    }
}
