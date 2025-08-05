package com.playground.api.config;

import com.playground.api.model.Vehicle;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Vehicle sample bean configuration
@Configuration
public class BeanConfiguration {

    @Bean
    Vehicle vehicle1() {
        var veh = new Vehicle();
        veh.setName("Honda");
        return veh;
    }

//    @Bean
//    public FlywayMigrationStrategy cleanMigrateStrategy() {
//        return flyway -> {
//            flyway.repair();
//            flyway.migrate();
//        };
//    }
}
