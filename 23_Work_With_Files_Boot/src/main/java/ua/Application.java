package ua;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ua.entity.Role;
import ua.entity.UserEntity;
import ua.repository.UserEntityRepository;

@SpringBootApplication
@ImportAutoConfiguration(classes=WebMvcAutoConfiguration.class)
public class Application extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
		addAdmin(run);
	}
	
	static void addAdmin(ConfigurableApplicationContext run){
		UserEntityRepository repository = run.getBean(UserEntityRepository.class);
		UserEntity entity = repository.findForAuth("admin");
		if(entity==null){
			PasswordEncoder encoder = run.getBean(PasswordEncoder.class);
			entity = new UserEntity();
			entity.setEmail("admin");
			entity.setPassword(encoder.encode("admin"));
			entity.setRole(Role.ROLE_ADMIN);
			repository.save(entity);
		}
	}
	
	@Value("${file.path}")
	private String path;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		resolver.setOneIndexedParameters(true);
		argumentResolvers.add(resolver);
		super.addArgumentResolvers(argumentResolvers);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/*").addResourceLocations("file:"+path).setCachePeriod(Integer.MAX_VALUE);
		super.addResourceHandlers(registry);
	}
	
	
}