var score = 0;
var rounds = 0;
var kerrat = 0;
var mem = {
    // (A) PROPERTIES
    // (A1) HTML ELEMENTIT
    hWrap : null, //HTML peli wrapper
    hCards :null, //HTML kortit
    // (A2) PELIN ASETUKSET
    sets : 8, // Total korttien määrä
    hint : 1000, //Kuinka pitkään näyttää mismatched kortit
    url : "", // URL kuville
    // (A3) FLAGS
    loaded : 0, //Ladattujen Assettijen määrä
    moves : 0, // Total number of moves
    last : null, //Viimeksi avattu kortti
    grid : null, //Tämänhetkinen peli grid
    matches : null, //Tämänhetkiset parilliset kortit (eli matched)
    locked : null, //2 korttia valittu jotka ei parillisia (did not match)

    // (B) PRELOAD
    preload : function () {
        // (B1) HANKI HTML PELI WRAPPER (GET)
        mem.hWrap = document.getElementById("mem-game");

        // (B2) ESI LATAA KUVAT (PRELOAD)
        for (let i=0; i<=mem.sets; i++) {
            let img = document.createElement("img");
            img.onload = function() {
                mem.loaded++;
                if (mem.loaded == mem.sets+1) { mem.init(); }
            };
            img.src = mem.url+"hentai"+i+".jpg";
        }
    },

    // (C) INIT PELI
    init : function () {
        // (C1) RESET
        if (mem.locked != null) {
            clearTimeout(mem.locked);
            mem.locked = null;
        }
        mem.hCards = [];
        mem.grid = [];
        mem.matches = [];
        mem.moves = 0 ;
        mem.last = null;
        mem.locked = null;
        mem.hWrap.innerHTML = "";

        // (C2) RANDOM RESHUFFLE KORTEILLE
        let current = mem.sets * 2, temp, random;
        for (var i=1; i<= mem.sets; i++) {
            mem.grid.push(i);
            mem.grid.push(i);
        }
        while (0 !== current) {
            random = Math.floor(Math.random() * current);
            current -= 1;
            temp = mem.grid[current];
            mem.grid[current] = mem.grid[random];
            mem.grid[random] = temp;
        }

        // (C3) LUO HTML KORTIT
        for (let i=0; i<mem.sets * 2; i++) {
        let card = document.createElement("div");
        card.className = "mem-card";
        card.innerHTML = `<img src='${mem.url}hentai0.jpg'/>`;
        card.dataset.idx = i;
        card.onclick = mem.open;
        mem.hWrap.appendChild(card);
        mem.hCards.push(card);
        }
    },

    // (D) AVAA KORTTI
    open : function () { if (mem.locked == null) {
        // (D1) AVAA VALITUT KORTIT +1kerta
        mem.moves++;
        let idx = this.dataset.idx;
        this.innerHTML = `<img src='${mem.url}hentai${mem.grid[idx]}.jpg'/>`;
        this.onclick = "";
        this.classList.add("open");

        // (D2) EI EDELLISTÄ ARVAUSTA VAIN RECORDED AS OPENED
        if (mem.last == null) { mem.last =idx; }
        else {
            // (D3) MATCHED AGAINST PREVIOUS GUESS (ELI MATCHED ELI KUN ARVAAT 2 KORTTIA OIKEIN) +1rounds + reset kerrat
            if (mem.grid[idx] == mem.grid[mem.last]) {
                mem.matches.push(mem.last);
                mem.matches.push(idx);
                mem.hCards[mem.last].classList.remove("open");
                mem.hCards[idx].classList.remove("open");
                mem.hCards[mem.last].classList.add("right");
                mem.hCards[idx].classList.add("right");
                mem.last = null;
                score += 1;
                document.getElementById('score').innerHTML = score;
                if (mem.matches.length == mem.sets *2) {
                    timeMsg();
                }
            }

            // (D4) NOT MATCHED - SULJE MOLEMMAT KORTIT ONLY AFTER A WHILE (ELI KUN ARVAAT VÄÄRIN)
            else {
                kerrat += 1;
                document.getElementById('kerrat').innerHTML = kerrat;
                miinus();
                mem.hCards[mem.last].classList.add("wrong");
                mem.hCards[idx].classList.add("wrong");
                mem.locked = setTimeout(function(){
                    mem.close(idx, mem.last);
                }, mem.hint);
            }
        }
    }
    },

    // (E) CLOSE PREVIOUSLY MIS-MATCHED CARDS (ELI SULKEE VÄÄRINARVATUT KORTIT)
    close: function (aa, bb) {
        aa = mem.hCards[aa];
        bb= mem.hCards[bb];
        aa.classList.remove("wrong");
        bb.classList.remove("wrong");
        aa.classList.remove("open");
        bb.classList.remove("open");
        aa.innerHTML = `<img src='${mem.url}hentai0.jpg'/>`;
        bb.innerHTML = `<img src='${mem.url}hentai0.jpg'/>`;
        aa.onclick = mem.open;
        bb.onclick = mem.open;
        mem.locked = null;
        mem.last = null;
    }
};

// (F) INIT GAME
window.addEventListener("DOMContentLoaded", mem.preload);

// (G) VIIVETTÄ VOITO ALERTILLE (itse alertti tapahtuu kohdassa D3)
    // (G1) VOITTO ALERT + reset
    function alertMsg()
    {
       alert("SINÄ VOITIT TÄMÄN KIERROKSEN! || Total Moves: " + mem.moves + ' || score: ' + score + ' || Voitetut Kierrokset: ' + rounds + ' ᕕ( ᐛ )ᕗ ||');
       mem.init();
    }
    // (G2) RESET KERRAT
    function times() {
     kerrat = 0;
     document.getElementById('kerrat').innerHTML = kerrat;
    }
    // (G3) KIERROS+1
    function kierrokset() {
      rounds += 1;
     document.getElementById('rounds').innerHTML = rounds;
    }
    // (G4) VIIVE (kierrokset -> alert -> reset kerrat)
    function timeMsg() {
        setTimeout("kierrokset()",100);
        setTimeout("alertMsg()",200);
        setTimeout("times()",300);
    }

// (H) MIINUS JOKA 5:nnes KERTA
    // (H1) MIINUS PISTE (JOKA 5:nnes KERTA (tapahtuu kohdassa D4))
    function miinus() {
        if (kerrat % 5 == 0) {
            if (score >= 1) { 
            score -= 1;
            document.getElementById('score').innerHTML = score;
            }
        }
    }