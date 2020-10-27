package com.example.demo;

import com.example.demo.model.Buyer;
import com.example.demo.reposetory.BuyerRepoJDBC;
import com.example.demo.service.Scala;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import outside.Glojure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ComponentScan(basePackages = {"outside","com.example.demo"})
@ImportResource("classpath:app-config.xml")
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
        Glojure glojure = (Glojure) ctx.getBean("glojure");
        System.out.println(glojure.learnMe());
        Scala scala = (Scala) ctx.getBean("scala");
        System.out.println(scala.learnMe());

        BuyerRepoJDBC buyerRepoJDBC =  ctx.getBean(BuyerRepoJDBC.class);
        buyerRepoJDBC.save(new Buyer(1L, "Dima", "Russia", 1000 ));
        buyerRepoJDBC.save(new Buyer(2L, "Elen", "USA", 1500 ));
        System.out.println(buyerRepoJDBC.findById("1"));
        System.out.println("--------------");
        buyerRepoJDBC.findAll().forEach(System.out::println);


    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ConfigurableApplicationContext ctx) {
//        return args -> {
//            System.out.println("Some spring's beans");
//            String[] beans = ctx.getBeanDefinitionNames();
//            Arrays.stream(beans).sorted().forEach(System.out::println);
//        };
//    }
}
