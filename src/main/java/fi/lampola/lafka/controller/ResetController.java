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
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Markus
 */
@Controller
@RequestMapping("/reset")
public class ResetController {

    @Autowired
    HenkiloRepository henkiloRepository;
    
    @RequestMapping(value="{salt}", method = RequestMethod.GET)
    public String list(Model model, @PathVariable String salt) {
        model.addAttribute("henkilo", henkiloRepository.findBySalt(salt));
        return "reset";
    }

    @RequestMapping(value="{salt}", method = RequestMethod.POST)
    public String list(Model model, @PathVariable String salt, @RequestParam String password) {
        Henkilo henkilo = henkiloRepository.findBySalt(salt);
        
        if (henkilo != null) {
            henkilo.setPassword(password);
            henkiloRepository.save(henkilo);
        }
        
        return "redirect:/";
    }
}
