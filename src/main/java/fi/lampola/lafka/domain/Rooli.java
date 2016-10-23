/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Markus
 */
@Entity
public class Rooli  extends AbstractPersistable<Long> {
    private String nimi;
    private String kuvaus;

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
}
