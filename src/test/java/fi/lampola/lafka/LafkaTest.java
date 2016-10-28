package fi.lampola.lafka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fi.lampola.lafka.domain.Henkilo;
import fi.lampola.lafka.domain.Rooli;
import fi.lampola.lafka.repository.HenkiloRepository;
import fi.lampola.lafka.repository.RooliRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Markus
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LafkaTest {

    @Autowired
    HenkiloRepository henkiloRepository;
            
    @Autowired
    private RooliRepository rooliRepository;

    public LafkaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHenkilo() {
        Rooli rooli = new Rooli();
        rooli.setNimi("TEST");
        rooli.setKuvaus("Testaaja");

        rooliRepository.save(rooli);

        String etunimi = "Albert";
        String sukunimi = "Edelfeldt";
        String email = "null@dev.null";
        
        Henkilo henkilo = new Henkilo();
        henkilo.setEmail(email);
        henkilo.setPassword("ae");
        henkilo.setEtunimi(etunimi);
        henkilo.setSukunimi(sukunimi);
        henkilo.setKatuosoite("Aleksanterinkatu 13");
        henkilo.setKaupunki("Helsinki");
        henkilo.setRooli(rooli);

        henkiloRepository.save(henkilo);
        
        Henkilo dbHenkilo = henkiloRepository.findByEmail(email);
        assertNotNull(dbHenkilo);
        assertEquals(etunimi, dbHenkilo.getEtunimi());
        assertEquals(sukunimi, dbHenkilo.getSukunimi());
        assertEquals(email, dbHenkilo.getEmail());
        assertEquals(henkilo.getRooli().getNimi(), dbHenkilo.getRooli().getNimi());
    }
}
