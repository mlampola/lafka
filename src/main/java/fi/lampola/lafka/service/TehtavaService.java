/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.service;

import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.domain.Tehtava;
import fi.lampola.lafka.domain.display.DisplayTehtava;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Markus
 */
@Service
public class TehtavaService {
    
    public List<DisplayTehtava> getDisplayObj(List<Tehtava> tehtavat, List<Henkilo> henkilot) {
        List<DisplayTehtava> displayTehtavat = new ArrayList();
        
        for (Tehtava tehtava : tehtavat) {
            displayTehtavat.add(new DisplayTehtava(tehtava, henkilot));
        }
        
        return displayTehtavat;
    }
}
