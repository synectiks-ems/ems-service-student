package com.synectiks.student;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.synectiks.student.config.ApplicationProperties;
import com.synectiks.student.config.DefaultProfileUtil;
import com.synectiks.student.utils.SynectiksJPARepo;

import io.github.jhipster.config.JHipsterConstants;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SynectiksJPARepo.class)
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
public class StudentApp {

    private static final Logger log = LoggerFactory.getLogger(StudentApp.class);

    private static ConfigurableApplicationContext ctx = null;
    
    private final Environment env;

    private static String serverIp;

    public StudentApp(Environment env) {
        this.env = env;
    }

    /**
     * Initializes Student.
     * <p>
     * Spring profiles can be configured with a program argument --spring.profiles.active=your-active-profile
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="https://www.jhipster.tech/profiles/">https://www.jhipster.tech/profiles/</a>.
     */
    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                "with both the 'dev' and 'prod' profiles at the same time.");
        }
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)) {
            log.error("You have misconfigured your application! It should not " +
                "run with both the 'dev' and 'cloud' profiles at the same time.");
        }
        
    }

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(StudentApp.class);
        DefaultProfileUtil.addDefaultProfile(app);
        ctx  = app.run(args);
        Environment env = ctx.getEnvironment();

        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
            serverIp = hostAddress;
        } catch (Exception e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}\n\t" +
                "External: \t{}://{}:{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            env.getProperty("server.port"),
            protocol,
            hostAddress,
            env.getProperty("server.port"),
            env.getActiveProfiles());
    }

    public static <T> T getBean(Class<T> cls) {
		return ctx.getBean(cls);
	}
    
    public static Environment getEnvironment() {
		return ctx.getEnvironment();
	}
	
	public static int getServerPort() {
		return Integer.parseInt(ctx.getEnvironment().getProperty("server.port"));
	}
	
	public static String getServer() {
		return serverIp;
	}
}
