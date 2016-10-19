/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.controller;

import fi.lampola.lafka.repository.HenkiloRepository;
import fi.lampola.lafka.service.GeoRestClient;
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
    private HenkiloRepository henkiloRepository;

    @Autowired
    private GeoRestClient geoRepository;

    @PostConstruct
    private void init() {
        this.geoRepository.setUri("https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyCxiikL3ZQ9Jums1CvyjTbc6j0I0nJVdoA&address=");
    }
    
    @RequestMapping(value="*", method = RequestMethod.GET)
    public String home(Model model) {
        return "index";
    }

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("henkilot", henkiloRepository.findAll());
        return "signup";
    }
}
