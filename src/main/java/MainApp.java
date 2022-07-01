


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.")
public class MainApp {
	public static void main(String[] args) {
		System.setProperty("server.port", "8080");

		SpringApplication app = new SpringApplication(MainApp.class);

		app.run();
	}
}