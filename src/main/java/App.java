import static spark.Spark.*;
import java.util.Random;

public class App {

	public static void main(String[] args) {
		
		// Figure out which port our application should run on
		int port = 8080;
		String httpPlatformPort = System.getenv("HTTP_PLATFORM_PORT");
		
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		else if (httpPlatformPort != null) {
			try {
				port = Integer.parseInt(httpPlatformPort);
			} catch (Exception ex) {
				System.out.println("Failed to parse HTTP_PLATFORM_PORT: '" + httpPlatformPort + "'");
			}
		}
		
		port(port);

		staticFileLocation("/content");
		
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

		get("/", (req, res) -> { res.redirect("/8-ball.html"); return ""; });
		get("/ask-8-ball", (req, res) -> eightBallAnswers[r.nextInt(eightBallAnswers.length)]);
	}
}
