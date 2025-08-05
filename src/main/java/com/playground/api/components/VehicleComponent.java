package com.playground.api.components;

import com.playground.api.config.BeanConfiguration;
import com.playground.api.model.Vehicle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
// Get Vehicle bean sample configuration
@Component
@Slf4j
public class VehicleComponent implements CommandLineRunner {
    // throws org.springframework.beans.factory.NoUniqueBeanDefinitionException
    @Override
    public void run(String... args) throws Exception {
        var context = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        Vehicle vehicle = context.getBean(Vehicle.class);
        // It will throw error because we have two vehicle beans declared
        log.info("Vehicle name: {}", vehicle.getName());
    }
}
