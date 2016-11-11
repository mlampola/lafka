var rMaa = 6378.0; // km

var ilmansuunnat = [
    {alku: -Math.PI, loppu: -Math.PI * 7 / 8, suunta: "Länsi"},
    {alku: -Math.PI * 7 / 8, loppu: -Math.PI * 5 / 8, suunta: "Lounas"},
    {alku: -Math.PI * 5 / 8, loppu: -Math.PI * 3 / 8, suunta: "Etelä"},
    {alku: -Math.PI * 3 / 8, loppu: -Math.PI * 1 / 8, suunta: "Kaakko"},
    {alku: -Math.PI * 1 / 8, loppu: Math.PI * 1 / 8, suunta: "Itä"},
    {alku: Math.PI * 1 / 8, loppu: Math.PI * 3 / 8, suunta: "Koillinen"},
    {alku: Math.PI * 3 / 8, loppu: Math.PI * 5 / 8, suunta: "Pohjoinen"},
    {alku: Math.PI * 5 / 8, loppu: Math.PI * 7 / 8, suunta: "Luode"},
    {alku: Math.PI * 7 / 8, loppu: Math.PI, suunta: "Länsi"}
];

$(document).ready(function () {
    $.getJSON("/api/v1/display/myyjat/" + $('#asiakkaat').val(),
            function (asiakas) {
                console.log(asiakas);
                for (var i in asiakas.myyjat) {
                    var m = asiakas.myyjat[i];
                    console.log(m);
                    $("#myyjat").append(valinta(m.id, m.nimi, asiakas.longitudi, asiakas.latitudi, m.longitudi, m.latitudi));
                }
            }
    );
});

function valinta(id, nimi, lng1, lat1, lng2, lat2) { // nimen viittaama olio pisteessä (lng1, lat1)          
    var opt = $("<option/>").val(id);
    opt.text(nimi + " " + etäisyys(lng1, lat1, lng2, lat2) + " km " + suunta(lng1, lat1, lng2, lat2));
    return opt;
}

function etäisyys(lng1, lat1, lng2, lat2) {
    return Math.sqrt((
            Math.pow((lng2 - lng1) * Math.cos(lat1 * 2 * Math.PI / 360) * 2 * Math.PI / 360 * rMaa, 2) +
            Math.pow((lat2 - lat1) * 2 * Math.PI / 360 * rMaa, 2))).toFixed(1);
}

function suunta(lng1, lat1, lng2, lat2) { // verrattuna pisteeseen (lng1, lat1)               
    var kulma = Math.atan2(lat2 - lat1, (lng2 - lng1) * Math.cos(lat1 * 2 * Math.PI / 360)).toFixed(5);
    var idx = 0;

    for (i = 0; ilmansuunnat[i].loppu < kulma && i < ilmansuunnat.length; i++) {
        ++idx;
    }

    return ilmansuunnat[idx].suunta;
}
