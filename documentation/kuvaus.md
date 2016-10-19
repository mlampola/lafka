Sovellus on tarkoitettu myyntiedustajien toiminnan organisoimiseen. Järjestelmään syötetään myyjät ja asiakkaat. Myyntipäällikkö jakaa edustajille tehtävät mahdollisimman optimaalisesti, ts. asiakkaan lähimmälle edustajalle. Siksi asiakkaita ja myyjiä syötettäessä heille haetaan Googlen REST-palvelusta koordinaatit katuosoitteen perusteella.

Jatkossa koordinaattien avulla on tarkoitus lajitella tehtävät myyjäehdokkaille asiakkaiden etäisyyden mukaiseen järjestykseen. Mahdollisesti tehtävään voi toteuttaa myös graafisen käyttöliittymän.

Toteuttamatta on syötteiden validointi, tunnistautuminen ja roolit.

Sovelluksen muutokset testataan automaattisesti Travis-palvelussa ja onnistuneiden testien jälkeen sovellus päivitetään Herokuun.


