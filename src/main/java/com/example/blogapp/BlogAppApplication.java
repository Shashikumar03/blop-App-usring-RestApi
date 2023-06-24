package com.example.blogapp;

import com.example.blogapp.Repository.RoleRepository;
import com.example.blogapp.config.AppConst;
import com.example.blogapp.entities.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApplication  implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    public static void main(String[] args) {
        SpringApplication.run(BlogAppApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


    @Override
    public void  run(String... args)throws  Exception{
        System.out.println("encode of Shashi@123 "+passwordEncoder.encode("Shashi@123"));
        try {
            Role role= new Role();
            role.setId(AppConst.ADMIN_USER);
            role.setName("ROLE_ADMIN");

            Role role1= new Role();
            role1.setId(AppConst.Normal_USER);
            role1.setName("ROLE_NORMAL");

            List<Role> roles = List.of(role, role1);
            List<Role> roles1 = this.roleRepository.saveAll(roles);
            roles1.forEach(r->{
                System.out.println(r.getName());
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
