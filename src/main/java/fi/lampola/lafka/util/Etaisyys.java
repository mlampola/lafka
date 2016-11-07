/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.lampola.lafka.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Markus
 */
public class Etaisyys {

    private final static double R_MAA = 6378.0; // km
    private final static List<Ilmansuunta> ILMANSUUNNAT = new ArrayList();

    static {
        ILMANSUUNNAT.add(new Ilmansuunta(-Math.PI, -Math.PI * 7 / 8, "Länsi"));
        ILMANSUUNNAT.add(new Ilmansuunta(-Math.PI * 7 / 8, -Math.PI * 5 / 8, "Lounas"));
        ILMANSUUNNAT.add(new Ilmansuunta(-Math.PI * 5 / 8, -Math.PI * 3 / 8, "Etelä"));
        ILMANSUUNNAT.add(new Ilmansuunta(-Math.PI * 3 / 8, -Math.PI * 1 / 8, "Kaakko"));
        ILMANSUUNNAT.add(new Ilmansuunta(-Math.PI * 1 / 8, Math.PI * 1 / 8, "Itä"));
        ILMANSUUNNAT.add(new Ilmansuunta(Math.PI * 1 / 8, Math.PI * 3 / 8, "Koillinen"));
        ILMANSUUNNAT.add(new Ilmansuunta(Math.PI * 3 / 8, Math.PI * 5 / 8, "Pohjoinen"));
        ILMANSUUNNAT.add(new Ilmansuunta(Math.PI * 5 / 8, Math.PI * 7 / 8, "Luode"));
        ILMANSUUNNAT.add(new Ilmansuunta(Math.PI * 7 / 8, Math.PI, "Länsi"));
    }

    private static class Ilmansuunta {

        private final double alkukulma; // radiaaneina
        private final double loppukulma;
        private final String nimi;

        public Ilmansuunta(double alkukulma, double loppukulma, String nimi) {
            this.alkukulma = alkukulma;
            this.loppukulma = loppukulma;
            this.nimi = nimi;
        }

        public double getAlkukulma() {
            return alkukulma;
        }

        public double getLoppukulma() {
            return loppukulma;
        }

        public String getNimi() {
            return nimi;
        }
    }

    public static double laskeEtaisyys(double lng1, double lat1, double lng2, double lat2) {
        return Math.sqrt(
                (Math.pow((lng2 - lng1) * Math.cos(lat1 * 2 * Math.PI / 360) * 2 * Math.PI / 360 * R_MAA, 2)
                + Math.pow((lat2 - lat1) * 2 * Math.PI / 360 * R_MAA, 2)));
    }

    public static String laskeSuunta(double lng1, double lat1, double lng2, double lat2) {
        double kulma = Math.atan2(lat2 - lat1, (lng2 - lng1) * Math.cos(lat1 * 2 * Math.PI / 360));
        int i = 0;

        for (i = 0; ILMANSUUNNAT.get(i).getLoppukulma() < kulma && i < ILMANSUUNNAT.size(); i++) {
            ;
        }

        return ILMANSUUNNAT.get(i).getNimi();
    }
}
