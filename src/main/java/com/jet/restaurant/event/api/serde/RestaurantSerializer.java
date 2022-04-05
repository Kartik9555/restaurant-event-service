package com.jet.restaurant.event.api.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jet.restaurant.event.api.topic.Restaurant;
import org.apache.kafka.common.serialization.Serializer;

public class RestaurantSerializer implements Serializer<Restaurant> {

    @Override
    public byte[] serialize(String s, Restaurant restaurant) {
        byte[] retVal = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            retVal = mapper.writeValueAsString(restaurant).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }
}
