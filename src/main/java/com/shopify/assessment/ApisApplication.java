package com.shopify.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The main class.
 */
@EnableSwagger2
@SpringBootApplication
public class ApisApplication {

  /**
   * Main to run the application.
   *
   * @param args - args to main.
   */
  public static void main(String[] args) {
    SpringApplication.run(ApisApplication.class, args);
  }

  /**
   * Corresponding bean created for swagger v2.
   *
   * @return - Interface of a swagger.
   */
  @Bean
  public Docket swaggerApi() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.shopify.assessment")).build();
  }

}
