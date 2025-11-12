package com.curahub.curahub.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	private static final List<String> ALLOWED_ORIGINS = List.of(
			"http://127.0.0.1:5500",
			"http://localhost:5500",
			"http://127.0.0.1:5501",
			"http://localhost:5501"
	);

	private static final List<String> ALLOWED_PATTERNS = List.of(
			"http://127.0.0.1:*",
			"http://localhost:*"
	);

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins(ALLOWED_ORIGINS.toArray(String[]::new))
				.allowedOriginPatterns(ALLOWED_PATTERNS.toArray(String[]::new))
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(false)
				.maxAge(3600);
	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(false);
		configuration.setAllowedOrigins(ALLOWED_ORIGINS);
		configuration.setAllowedOriginPatterns(ALLOWED_PATTERNS);
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(source);
	}
}


