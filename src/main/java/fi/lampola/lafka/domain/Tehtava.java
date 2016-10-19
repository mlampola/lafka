/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Markus
 */
@Entity
public class Tehtava extends AbstractPersistable<Long>{
    
    @ManyToOne
    private Henkilo myyja;
    
    @ManyToOne
    private Asiakas asiakas;
    
    private Integer arvioituMyynti;
    private Integer toteutunutMyynti;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date valmistumisPaiva;

    public Henkilo getMyyja() {
        return myyja;
    }

    public void setMyyja(Henkilo myyja) {
        this.myyja = myyja;
    }

    public Asiakas getAsiakas() {
        return asiakas;
    }

    public void setAsiakas(Asiakas asiakas) {
        this.asiakas = asiakas;
    }

    public Integer getArvioituMyynti() {
        return arvioituMyynti;
    }

    public void setArvioituMyynti(Integer arvioituMyynti) {
        this.arvioituMyynti = arvioituMyynti;
    }

    public Integer getToteutunutMyynti() {
        return toteutunutMyynti;
    }

    public void setToteutunutMyynti(Integer toteutunutMyynti) {
        this.toteutunutMyynti = toteutunutMyynti;
    }

    public Date getValmistumisPaiva() {
        return valmistumisPaiva;
    }

    public void setValmistumisPaiva(Date valmistumisPaiva) {
        this.valmistumisPaiva = valmistumisPaiva;
    }
}
