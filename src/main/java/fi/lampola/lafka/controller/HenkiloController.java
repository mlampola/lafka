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
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private RooliRepository rooliRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("henkilot", henkiloRepository.findAll());
        model.addAttribute("roolit", rooliRepository.findAll());
        return "signup";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String create(HttpServletRequest request, @ModelAttribute Henkilo henkilo, @RequestParam Long rooliId) {
        Rooli rooli = rooliRepository.findOne(rooliId);
        henkilo.setRooli(rooli);
        henkiloService.add(henkilo, true, getApplicationBaseUrl(request));
        return "redirect:/henkilot";
    }

    public static String getApplicationBaseUrl(HttpServletRequest request) {
        String url;

        String scheme = request.getScheme() + "://";
        String serverName = request.getServerName();
        String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
        String contextPath = request.getContextPath();

        url = scheme + serverName + serverPort + contextPath;

        return url;
    }
}
