package dev.dhanush.FileSharing.Repository;

import dev.dhanush.FileSharing.Model.Role;
import dev.dhanush.FileSharing.Model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.ANY)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateUserWithRoles() {
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleRepository.save(roleUser);

        Set<Role> roles = new HashSet<>();
        roles.add(roleAdmin);
        roles.add(roleUser);

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRoles(roles);
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("testuser").orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testuser");
        assertThat(foundUser.getRoles()).hasSize(2).extracting("name").contains("ROLE_ADMIN", "ROLE_USER");
    }
}