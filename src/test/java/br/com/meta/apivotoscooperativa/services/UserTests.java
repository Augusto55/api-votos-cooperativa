package br.com.meta.apivotoscooperativa.services;
import br.com.meta.apivotoscooperativa.model.Users;
import br.com.meta.apivotoscooperativa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {
    @Autowired
    private UserRepository userRepository;

    @org.junit.Test
    public void testDatabaseConnection() {
        Users user = new Users("John Doe");
        userRepository.save(user);

        System.out.println("User saved with ID: " + user.getId());

        Users retrievedUser = userRepository.findById(user.getId()).orElse(null);
        System.out.println("Retrieved user: " + retrievedUser);
    }
}
