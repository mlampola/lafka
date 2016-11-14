/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.domain.display;

import fi.lampola.lafka.domain.Asiakas;
import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.domain.Tehtava;
import fi.lampola.lafka.repository.HenkiloRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Markus
 */
public class DisplayTehtava {
    
    private Long id;
    private DisplayHenkilo myyja;    
    private DisplayAsiakas asiakas;

    public DisplayTehtava(Tehtava tehtava, List<Henkilo> henkilot) {
        this.id = tehtava.getId();
        this.myyja = new DisplayHenkilo(tehtava.getAsiakas(), tehtava.getMyyja());        
        this.asiakas = new DisplayAsiakas(tehtava.getAsiakas(), henkilot);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DisplayHenkilo getMyyja() {
        return myyja;
    }

    public void setMyyja(DisplayHenkilo myyja) {
        this.myyja = myyja;
    }

    public DisplayAsiakas getAsiakas() {
        return asiakas;
    }

    public void setAsiakas(DisplayAsiakas asiakas) {
        this.asiakas = asiakas;
    }
}
