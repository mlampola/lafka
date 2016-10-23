/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.controller;

import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.repository.HenkiloRepository;
import fi.lampola.lafka.service.GeoRestClient;
import fi.lampola.lafka.service.HenkiloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Markus
 */
@Controller
@RequestMapping("/henkilot")
public class HenkiloController {

    @Autowired
    private HenkiloService henkiloService;

    @Autowired
    HenkiloRepository henkiloRepository;
    
    @Autowired
    private GeoRestClient geoRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("henkilot", henkiloRepository.findAll());
        return "signup";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Henkilo henkilo) {
        henkiloService.add(henkilo, true);
        return "redirect:/henkilot";
    }
}
