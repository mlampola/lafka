/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.auth;

import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.repository.HenkiloRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 *
 * @author Markus
 */
@Component
public class JpaAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    private HenkiloRepository henkiloRepository;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getPrincipal().toString();
        String password = a.getCredentials().toString();

        Henkilo henkilo = henkiloRepository.findByEmail(username);

        if (henkilo == null) {
            throw new AuthenticationException("Unable to authenticate user " + username) {
            };
        }

        if (!BCrypt.hashpw(password, henkilo.getSalt()).equals(henkilo.getPassword())) {
            throw new AuthenticationException("Unable to authenticate user " + username) {
            };
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority("USER"));

        return new UsernamePasswordAuthenticationToken(henkilo.getEmail(), password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
}
