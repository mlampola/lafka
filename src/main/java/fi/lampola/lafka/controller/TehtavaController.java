/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.controller;

import fi.lampola.lafka.domain.Asiakas;
import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.domain.Tehtava;
import fi.lampola.lafka.repository.AsiakasRepository;
import fi.lampola.lafka.repository.HenkiloRepository;
import fi.lampola.lafka.repository.TehtavaRepository;
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
@RequestMapping("/tehtavat")
public class TehtavaController {

    @Autowired
    private HenkiloRepository henkiloRepository;

    @Autowired
    private AsiakasRepository asiakasRepository;

    @Autowired
    private TehtavaRepository tehtavaRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("tehtavat", tehtavaRepository.findAll());
        model.addAttribute("henkilot", henkiloRepository.findAll());
        model.addAttribute("asiakkaat", asiakasRepository.findAll());
        return "tehtavat";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestParam String asiakasId , @RequestParam String myyjaId) {
        Tehtava tehtava = new Tehtava();
        Henkilo myyja = henkiloRepository.findOne(Long.parseLong(myyjaId));
        tehtava.setMyyja(myyja);
        Asiakas asiakas = asiakasRepository.findOne(Long.parseLong(asiakasId));
        tehtava.setAsiakas(asiakas);
        tehtavaRepository.save(tehtava);
        return "redirect:/tehtavat";
    }
    @RequestMapping(value="{tehtavaId}/myyja", method = RequestMethod.POST)
    public String assign(@PathVariable String tehtavaId , @RequestParam String myyjaId) {
        Tehtava tehtava = tehtavaRepository.findOne(Long.parseLong(tehtavaId));
        Henkilo myyja = henkiloRepository.findOne(Long.parseLong(myyjaId));
        tehtava.setMyyja(myyja);
        tehtavaRepository.save(tehtava);
        return "redirect:/tehtavat";
    }
}
