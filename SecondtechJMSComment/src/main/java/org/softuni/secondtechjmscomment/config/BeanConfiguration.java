package org.softuni.secondtechjmscomment.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.softuni.secondtechjmscomment.domain.models.binding.CommentBindingModel;
import org.softuni.secondtechjmscomment.domain.models.binding.CommentUpdateBindingModel;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfiguration {

    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    private static final String COMMENT_TYPE = "org.softuni.secondtech.domain.models.binding.CommentBindingModel";
    private static final String COMMENT_UPDATE_TYPE = "org.softuni.secondtech.domain.models.binding.CommentUpdateBindingModel";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        return connectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        Map<String, Class<?>> typeId = new HashMap<>();
        //Important mapping (if class names are different)
        typeId.put(COMMENT_TYPE, CommentBindingModel.class);
        typeId.put(COMMENT_UPDATE_TYPE, CommentUpdateBindingModel.class);
        converter.setTypeIdMappings(typeId);
        return converter;
    }
}
