package org.softech.flexbet;

import config.SaleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SaleConfig.class})
public class FlexbetApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlexbetApplication.class, args);
	}
}
