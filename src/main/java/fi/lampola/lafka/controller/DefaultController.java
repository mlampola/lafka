/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.controller;

import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.domain.Rooli;
import fi.lampola.lafka.repository.HenkiloRepository;
import fi.lampola.lafka.repository.RooliRepository;
import fi.lampola.lafka.service.GeoRestClient;
import fi.lampola.lafka.service.HenkiloService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Markus
 */
@Controller
public class DefaultController {

    @Autowired
    private HenkiloService henkiloService;
    
    @Autowired
    private HenkiloRepository henkiloRepository;

    @Autowired
    private RooliRepository rooliRepository;

    @Autowired
    private GeoRestClient geoRepository;

    @PostConstruct
    private void init() {
        this.geoRepository.setUri("https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyCxiikL3ZQ9Jums1CvyjTbc6j0I0nJVdoA&address=");

        String adminRoleName = "ADMIN";
        String userRoleName = "USER";
        Rooli adminRole = rooliRepository.findByNimi(adminRoleName);
        Rooli userRole = rooliRepository.findByNimi(userRoleName);
        
        if (adminRole == null) {
            adminRole = new Rooli();
            adminRole.setNimi(adminRoleName);
            adminRole.setKuvaus("Ylläpitäjä");
            
            rooliRepository.save(adminRole);
        }
        
        if (userRole == null) {
            userRole = new Rooli();
            userRole.setNimi(userRoleName);
            userRole.setKuvaus("Käyttäjä");
            
            rooliRepository.save(userRole);
        }
        
        String adminEmail = "uuno@turhapuro.com";
        
        if (henkiloRepository.findByEmail(adminEmail) == null) {
            Henkilo admin = new Henkilo();
            admin.setEtunimi("Uuno");
            admin.setSukunimi("Turhapuro");
            admin.setEmail(adminEmail);
            admin.setPassword("sÖrsselssön");
            admin.setKatuosoite("Mannerheimintie 1");
            admin.setHuoneisto("A 1");
            admin.setKaupunki("Helsinki");
            admin.setRooli(adminRole);

            henkiloService.add(admin, false, "");
        }
    }
    
    @RequestMapping(value="*", method = RequestMethod.GET)
    public String home(Model model) {
        return "index";
    }

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("henkilot", henkiloRepository.findAll());
        model.addAttribute("roolit", rooliRepository.findAll());
        return "signup";
    }
}
