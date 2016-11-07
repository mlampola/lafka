/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.controller.rest;

import fi.lampola.lafka.domain.Asiakas;
import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.domain.display.DisplayAsiakas;
import fi.lampola.lafka.repository.AsiakasRepository;
import fi.lampola.lafka.repository.HenkiloRepository;
import fi.lampola.lafka.repository.TehtavaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Markus
 */
@RestController
@RequestMapping("/api/v1/display/myyjat")
public class RestTehtavaController {
    
    @Autowired
    private AsiakasRepository asiakasRepository;

    @Autowired
    private HenkiloRepository henkiloRepository;

    @RequestMapping(value="{asiakasId}", method = RequestMethod.GET)
    public DisplayAsiakas list(Model model, @PathVariable Long asiakasId) {
        Asiakas asiakas = asiakasRepository.findOne(asiakasId);
        List<Henkilo> henkilot = henkiloRepository.findAll();
        
        DisplayAsiakas da = null;
        
        if (asiakas != null) {
            da = new DisplayAsiakas(asiakas, henkilot);
        }
        
        return da;
    }
}
