package com.fsb.jwt_authentication.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    UserRepository repo;

    @Test
    public void testCreateUser(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "mark2020";
        String encodedPassword =passwordEncoder.encode(rawPassword);

        User newUser = new User("mark@gmail.com",encodedPassword);

        User savedUser = repo.save(newUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAssignRolesToUser(){
        Integer userId=3;


        User user=repo.findById(userId).get();
       /* user.addRole(new Role(roleId));


        user.getRoles().clear();
        User updatedUser = repo.save(user);*/
        System.out.println(user.getRoles());
        System.out.println(repo.findById(4).get().getRoles());
        assertThat(user.getRoles()).isNotNull();

    }

}
