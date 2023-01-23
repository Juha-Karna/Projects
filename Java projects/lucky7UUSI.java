package app;
import java.io.Console;
import java.util.Scanner;

import javax.annotation.processing.RoundEnvironment;

public class AskName2 {

public static void main(String[] args) {
		//Lucky 7 peli tekijä Juha Kärnä
		//Pelin säännöt
		//peli alkaa kun kirjoitat aloitus raha panoksen
		//Rahan jälkeen määrität monta kierrosta pelaat
		//Kun yksi kolmesta numerosta on 7 voitat 3€
		//Kun saat kaksi 7 peräkkäin (esim 771, 177) voitat 5€
		//Kun saat kolme 7 peräkkäin (esim 777) voitat 8€
		//Aina kun häivät menetät 1€
		//Jos haluat jatkaa peliä kirjoita 'kyllä' jos et kirjoita 'ei'
		
		Scanner in = new Scanner(System.in);

		Integer vastaus1 , vastaus2, vastaus3 , raha , currentEuros, tulos , kierros , round;
		String lossWin, jatkaa , terminate;
		game();
		System.out.println("Haluatko jatkaa? kirjoita 'kyllä' jos haluat, muulloin kirjoita 'ei'");
		jatkaa= in.nextLine();
		if (jatkaa.equals("kyllä")) {
		
			game();
		}
		if (jatkaa.equals("ei")) {
			System.out.println("Peli lopetettu.");
			return;
		}
		while (jatkaa != "ei") {
		System.out.println("Haluatko jatkaa? kirjoita 'kyllä' jos haluat, muulloin kirjoita 'ei'");
		jatkaa= in.nextLine();
		if (jatkaa.equals("kyllä")) {
		
			game();
		}
		else {
			System.out.println("Peli lopetettu.");
			return;
		}
		}
	}
	
	public static void game() {
		Scanner in = new Scanner(System.in);
		Integer vastaus1 , vastaus2, vastaus3 , raha , currentEuros, tulos , kierros , round;
		String lossWin, jatkaa;
		//(A) RAHA
				//(A1) Käyttäjän laittama raha määrä
				System.out.println("Kuinka monta euroa sinulla on?");
				raha = Integer.parseInt(in.nextLine());
				//(A2) Käyttäjän antama kierros määrä
				System.out.println("Kuinka monta kierrosta haluat pelata?");
				kierros = Integer.parseInt(in.nextLine());
				currentEuros = raha;
				round = 0 ;
				
				//(A3) Looppi kunnes raha on 0 tai max kierrokset on saavutettu 
				while (raha > 0) {
					kierros--;
					round++;
				
				//(B) NUMEROT
				//(B1) vastauksiin yksi random numero kymmenestä
				vastaus1 = (int) (Math.floor(Math.random()*10)+1);
				vastaus2 = (int) (Math.floor(Math.random()*10)+1);
				vastaus3 = (int) (Math.floor(Math.random()*10)+1);
				
				
				//(B2) printataan numerot
				System.out.println(vastaus1);
				System.out.println(vastaus2);
				System.out.println(vastaus3);
				
				//(C) NUMEROIDEN EHTOLAUSEET
				//(C1) jos numerot (vastaus1,2,3) on kolme peräkkäistä seiskaa
				if (vastaus1 == 7 && vastaus2 == 7 && vastaus3 == 7) {
					System.out.println("Voitit TRIPLAT");
					raha += 8;
				}
				//(C2)jos numerot (vastaus1,2,3) on kaksi peräkkäistä seiskaa
				else if(vastaus1 == 7 && vastaus2 == 7 || vastaus2 == 7 && vastaus3 == 7) {
					System.out.println("Voitit TUPLAT");
					raha += 5;
				}
				//(C3) Jos numerot (vastaus1,2,3) on yksi 7
				else if (vastaus1 == 7|| vastaus2 == 7 || vastaus3 == 7) {
					System.out.println("Voitit");
					raha += 3;
				}
				//(C4) muulloin -1
				else {
					System.out.println("Hävisit");
					raha -=  1;
				}
				//(C5) printtaa aina jälkeen paljon rahaa on
				System.out.println("Sinulla on " + raha + "€");
				
				//(D) LASKUT
				//(D1) paljon rahaa hävisit alkuperäisestä
				tulos = raha - currentEuros;
				//(D2) jos tulos isompi kuin nolla voitit muulloin hävisit
				if (tulos > 0) {
					lossWin= "Voitit";
				}
				else {
					lossWin= "Hävisit";
				}
				
				//(E) PELIN VOITTO/HÄVIÖ
				//(E1) kierroksiin limit
				if (kierros == 0) {
					System.out.println("Kaikki kierrokset "+ "("+ round +")" +" käytetty. Sinulla on " + raha + " Euroa. " + lossWin + " " + tulos + " euroa");
					break;
				}
				//(E2) jos raha on 0 niin game over
				if (raha <= 0) {
					System.out.println("Hävisit koko pelin. Rahat loppuivat. Menetit kaiken. selvisit " + round + " kierrosta");
					break;
				}
			}
	}
	
}