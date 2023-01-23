import java.util.Scanner;

public class AskName {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		
		Integer firstNum , lastNum;
	    
	    System.out.println("First number?");
	    firstNum = Integer.parseInt(in.nextLine()) -1;
	    
	    System.out.println("Last number?");
	    lastNum = Integer.parseInt(in.nextLine());
	    
	    while (firstNum < lastNum) {
	        firstNum++;
	    	System.out.println(firstNum);
	    }
		
	}

}
-----------------------------------------------------------------------------------------------------
import java.util.Random;
import java.util.Scanner;
public class arvauspeli {
public static void main(String[] args) {
    
Random r = new Random();
Scanner in = new Scanner(System.in);
String nimi;
int yritys = 0;

do
{
System.out.println("Guess my name (type stop to exit)");

nimi = in.nextLine();
if(nimi.equalsIgnoreCase("stop")) {
    break;
}
yritys += 1;
} while(!nimi.equalsIgnoreCase("Emma"));
        
if(nimi.equalsIgnoreCase("Emma")) {
System.out.println("Congratulations!");
}
System.out.println("You guessed " + yritys + " times.");

}}
------------------------------------------------------------------------------------------------------
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String[] words;
        words = new String[4];
        int valinta;

        words[0] = "Actions speak louder than words.";
        words[1] = "A barking dog never bites.";
        words[2] = "A penny saved is a penny earned.";
        words[3] = "All things come to those who wait.";

        System.out.println("Pick number from 1-4.");
        valinta = Integer.parseInt(in.nextLine());

        if(valinta==1) {
            System.out.println(words[0]);
        }
        else if(valinta==2) {
            System.out.println(words[1]);
        }
        else if(valinta==3) {
            System.out.println(words[2]);
        }
        else if(valinta==4) {
            System.out.println(words[3]);
        }
    }

}
-------------------------------------------------------------------------------------------------------
public class Test {
	public static void main(String[] args) {
		
		String[] furniture = {"Table", "Sofa", "Shelf", "Painting"};
		for (int i = 0; i < furniture.length; i++) {
        	if (furniture[i] == "Sofa") {
        	    System.out.println("Sofa found");
        	}
        }
	}
}
-------------------------------------------------------------------------------------------------------
public class Test {
	public static void main(String[] args) {
	    int[] numbers = {3 , 6 , 1};
	    int sum = 0;
	    for (int i = 0; i < numbers.length; i++){
	        sum = sum + numbers[i];
	    }
	    System.out.println(sum);
	    
	}
    
}
-------------------------------------------------------------------------------------------------------
public class Test {
	public static void main(String[] args) {
	    int[] numbers = {16, 18, 5, 3, 10};
		    int min = numbers[0];
		    for(int i=0; i<numbers.length; i++ ) {
	         if(numbers[i]<min) {
	            min = numbers[i];
	            
	         }
		   }
	    System.out.println(min);
	}
    
}
-------------------------------------------------------------------------------------------------------
import java.util.ArrayList;
import java.util.Collections;

public class Test {

	public static void main(String[] args) {

	    ArrayList<String> cars = new ArrayList<String>();
	    cars.add("Kia");
	    cars.add("Tesla");
	    cars.add("BMW");
	    cars.add("Renault");
	    
	     for (int i = 0; i < cars.size(); i++) {
	    	System.out.println(cars.get(i));
	    }
	    
	    System.out.println("MODIFIED LIST");
	    
	    cars.add(2, "Ford");
	    cars.remove(1);
	    cars.set(2, "Audi");
	    for (int i = 0; i < cars.size(); i++) {
	    	System.out.println(cars.get(i));
	    }
	    
	    System.out.println("SORTED LIST");
	   

	    Collections.sort(cars);
	    for (int i = 0; i < cars.size(); i++) {
	    	System.out.println(cars.get(i));
	    }
	    
	}
}
------------------------------------------------------------------------------------------------------
import java.util.Scanner;

public class AskName3 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		 double throw1 , throw2 , throw3;
		 int counter;
		 counter = 0 ;
		 System.out.println("Throw length");
		 throw1 = Double.parseDouble(in.nextLine());
		 System.out.println("Throw length");
		 throw2 = Double.parseDouble(in.nextLine());
		 System.out.println("Throw length");
		 throw3 = Double.parseDouble(in.nextLine());
		 double[] javelinThrows = {throw1 , throw2 , throw3};

		 for(int i = 0; i < javelinThrows.length; i++) {
			 counter++;
			 System.out.println("Throw " + counter + ": " + javelinThrows[i]);
		 }
    }

	}
------------------------------------------------------------------------------------------------------
import java.util.Scanner;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);


        ArrayList<String> shopingList = new ArrayList<String>();
        String input;

        do {
        System.out.println("Add item (x = exit)");
        input = in.nextLine();


        shopingList.add(input);

        if (input.equals("x")) {

            break;
        }



        } while (input != "x");

        shopingList.remove(shopingList.size() - 1);

        for (int i = 0; i < shopingList.size(); i++) {

            System.out.println(shopingList.get(i));

        }

	}
    
}
--------------------------------------------------------------------------------------------------------