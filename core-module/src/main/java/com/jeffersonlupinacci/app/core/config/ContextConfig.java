package com.jeffersonlupinacci.app.core.config;

import com.jeffersonlupinacci.app.core.SpringContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;

/**
 * The Context Configuration
 *
 * @author jeffersonlupinacci
 */
@Order(0)
@Configuration
@Import(SpringContext.class)
@ImportResource("spring/services.xml")
@ComponentScan(basePackages = {
    "com.jeffersonlupinacci.app.core.facade",
    "com.jeffersonlupinacci.app.core.exception"
})
public class ContextConfig {

}
