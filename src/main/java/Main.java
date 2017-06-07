import static spark.Spark.*;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		// Figure out which port our application should run on
		int port = 8080;
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		else if (System.getenv("HTTP_PLATFORM_PORT") != null)
			port = Integer.parseInt(System.getenv("HTTP_PLATFORM_PORT"));
			
		port(port);
		
		staticFiles.location("/public");
		
		Random r = new Random();
		String[] eightBallAnswers = {
		    "It is certain",
		    "It is decidedly so",
		    "Without a doubt",
		    "Yes definitely",
		    "You may rely on it",
		    "As I see it, yes",
		    "Most likely",
		    "Outlook good",
		    "Yes",
		    "Signs point to yes",
		    "Reply hazy try again",
		    "Ask again later",
		    "Better not tell you now",
		    "Cannot predict now",
		    "Concentrate and ask again",
		    "Don't count on it",
		    "My reply is no",
		    "My sources say no",
 		    "Outlook not so good",
		    "Very doubtful"
		};

		get("/ask-8-ball", (req, res) -> eightBallAnswers[r.nextInt(2)]);
		get("/derivative/:eq", (req, res) -> PowerRule(req.params("eq")));
	}
	
	// 5x^2 -> http://ravencs.club/derivative/5x^2
	public static String PowerRule(String input) {
		char[] equ = new char[input.length()];
		int xpos = 0;
		int coef = 0;
		String exp2 ="";
		int exp = 0;
		int newcoef;
		String coef2 = "";
		
		//place in character array
		
		
		for (int i = 0; i < input.length(); i++) {
			equ[i] = input.charAt(i);
		}

		if(equ[0]=='x'){
			return "1";
		}
		
		//this finds the position of x
		for (int i = 0; i < input.length(); i++) {
			if (equ[i] == 'x') {
				xpos = i;
				//this for loop creates a string for the coefficent, or all numbers before x
				for (int k = 0; k < xpos; k++) {
					coef2 += equ[k];
				}
				
				//this changes the string coefficient into an integer
				coef = Integer.parseInt(coef2);
				
				//this detects if x is the final term in the dervitive, if it isn't then it looks for the exponent
				if (xpos!=input.length()-1) {
					//if there is a ^, then the for loop creates a string of the exponent
					for (int j = i + 2; j < input.length(); j++) {
						exp2 += equ[j];
					}
					//this converts the string to a integer
					exp = Integer.parseInt(exp2);		
				}
				else{
					return "" + coef;
				}
					
			}

		}
		
		if(xpos==0){
			return "0";
		}
		else{
			newcoef=coef*exp;
			exp--;
			return newcoef+"x^"+exp;
		}

	}
}
