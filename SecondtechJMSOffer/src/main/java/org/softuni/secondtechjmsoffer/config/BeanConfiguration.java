package org.softuni.secondtechjmsoffer.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.modelmapper.ModelMapper;
import org.softuni.secondtechjmsoffer.models.binding.*;
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
    private static final String LAPTOP_TYPE = "org.softuni.secondtech.models.binding.LaptopOfferBindingModel";
    private static final String LAPTOP_UPDATE_TYPE = "org.softuni.secondtech.models.binding.LaptopUpdateBindingModel";
    private static final String LAPTOP_REMOVE_TYPE = "org.softuni.secondtech.models.binding.LaptopRemoveBindingModel";
    private static final String SMARTPHONE_TYPE = "org.softuni.secondtech.models.binding.SmartphoneOfferBindingModel";
    private static final String SMARTPHONE_UPDATE_TYPE = "org.softuni.secondtech.models.binding.SmartphoneUpdateBindingModel";
    private static final String SMARTPHONE_REMOVE_TYPE = "org.softuni.secondtech.models.binding.SmartphoneRemoveBindingModel";
    private static final String TABLET_TYPE = "org.softuni.secondtech.models.binding.TabletOfferBindingModel";
    private static final String TABLET_UPDATE_TYPE = "org.softuni.secondtech.models.binding.TabletUpdateBindingModel";
    private static final String TABLET_REMOVE_TYPE = "org.softuni.secondtech.models.binding.TabletRemoveBindingModel";

    private static final String CLOUDINARY_CLOUD_NAME = "secondtech";
    private static final String CLOUDINARY_API_KEY = "133179424763556";
    private static final String CLOUDINARY_API_SECRET = "19_tjQzx-KEF-Q1cSBNfjflo1h4";

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

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
        typeId.put(LAPTOP_TYPE, LaptopOfferBindingModel.class);
        typeId.put(LAPTOP_UPDATE_TYPE, LaptopUpdateBindingModel.class);
        typeId.put(LAPTOP_REMOVE_TYPE, LaptopRemoveBindingModel.class);
        typeId.put(SMARTPHONE_TYPE, SmartphoneOfferBindingModel.class);
        typeId.put(SMARTPHONE_UPDATE_TYPE, SmartphoneUpdateBindingModel.class);
        typeId.put(SMARTPHONE_REMOVE_TYPE, SmartphoneRemoveBindingModel.class);
        typeId.put(TABLET_TYPE, TabletOfferBindingModel.class);
        typeId.put(TABLET_UPDATE_TYPE, TabletUpdateBindingModel.class);
        typeId.put(TABLET_REMOVE_TYPE, TabletRemoveBindingModel.class);
        converter.setTypeIdMappings(typeId);
        return converter;
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUDINARY_CLOUD_NAME,
                "api_key", CLOUDINARY_API_KEY,
                "api_secret", CLOUDINARY_API_SECRET));
    }
}
