package com.jet.restaurant.event.api.serde;

import com.jet.restaurant.event.api.topic.Restaurant;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class RestaurantSerde implements Serde<Restaurant> {

    @Override
    public Serializer<Restaurant> serializer() {
        return new RestaurantSerializer();
    }

    @Override
    public Deserializer<Restaurant> deserializer() {
        return new RestaurantDeserializer();
    }

}
