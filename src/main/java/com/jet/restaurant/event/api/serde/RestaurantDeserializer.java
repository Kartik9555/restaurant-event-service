package com.jet.restaurant.event.api.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jet.restaurant.event.api.topic.Restaurant;
import org.apache.kafka.common.serialization.Deserializer;

public class RestaurantDeserializer implements Deserializer<Restaurant> {

    @Override
    public Restaurant deserialize(String s, byte[] bytes) {
        Restaurant restaurant = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            restaurant = mapper.readValue(bytes, Restaurant.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurant;
    }
}
