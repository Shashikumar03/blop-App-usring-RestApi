package com.example.blogapp;

import com.example.blogapp.Repository.RoleRepository;
import com.example.blogapp.config.AppConst;
import com.example.blogapp.entities.Role;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
//import springfox.documentation.oas.annotations.EnableOpenApi;

import java.util.List;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API",
        version = "2.0",
        description = "Employees Information",
        termsOfService = "terms",
        contact = @Contact(name = "shashi kumar",email = "shashi@gmail.com"),
        license = @License(name = "licence",url = "st.com")))
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
