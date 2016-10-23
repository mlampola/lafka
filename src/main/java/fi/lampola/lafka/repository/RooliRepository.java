/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.repository;

import fi.lampola.lafka.domain.Rooli;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Markus
 */
public interface RooliRepository extends JpaRepository<Rooli, Long> {
}
