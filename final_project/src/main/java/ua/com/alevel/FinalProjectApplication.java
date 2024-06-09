package ua.com.alevel;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.repository.user.ClientRepository;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@EnableAsync
@AllArgsConstructor
public class FinalProjectApplication {

    private final AdminRepository adminUserRepository;
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
//        Admin admin = new Admin();
//        admin.setEmail("admin@gmail.com");
//        admin.setPassword(encoder.encode("rootroot"));
//        adminUserRepository.save(admin);
//
//        Client client = new Client();
//        client.setEmail("pekaruk.ilyaa@gmail.com");
//        client.setPassword(encoder.encode("123456789"));
//        client.setFirstName("Ilya");
//        client.setLastName("Pekaruk");
//        client.setPhoneNumber("+380660235493");
//        client.setSex(Sex.MALE);
    }
}
