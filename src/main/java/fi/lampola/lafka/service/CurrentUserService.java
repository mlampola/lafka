/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.service;

import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.repository.HenkiloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Markus
 */
@Service
public class CurrentUserService {

    @Autowired
    HenkiloRepository henkiloRepository;

    public Henkilo getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        Henkilo henkilo = null;

        if (username != null) {
            henkilo = henkiloRepository.findByEmail(username);
        }

        return henkilo;
    }
}
