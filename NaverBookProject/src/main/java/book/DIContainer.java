package book;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIContainer {

	@Bean
	public BookSearch bookSearch() {
		return new BookSearch();
	}
}
