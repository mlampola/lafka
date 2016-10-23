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
import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Markus
 */
@Entity
public class Henkilo extends AbstractPersistable<Long>{

    private String etunimi;
    private String sukunimi;
    
    @Email
    @Column(unique = true)
    private String email;
    
    private String password;
    private String salt;

    private String katuosoite; // Esim. Helsinginkatu 20 (ilman rappua/huoneistoa)
    private String huoneisto; // Esim. A 1
    private String kaupunki;
    private String maa;
    
    @OneToMany(mappedBy="myyja")
    private List<Tehtava> tehtavat;
    
    // Paikkatiedot
    private Double longitudi;
    private Double latitudi;

    public Henkilo() {
        this.setMaa("Finland");
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.salt = BCrypt.gensalt();
        
        while (this.salt.contains("/") || this.salt.contains(".")) {
            this.salt = BCrypt.gensalt();
        }
        
        this.password = BCrypt.hashpw(password, this.salt);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
