package com.example.demo.Config;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public WKTReader wktReader() {
        return new WKTReader();
    }

    @Bean
    public JtsModule jtsModule() {
        return new JtsModule();
    }
}
