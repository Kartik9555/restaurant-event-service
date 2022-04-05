package com.jet.restaurant.event.api.serde;

import com.jet.restaurant.event.api.domain.Status;
import com.jet.restaurant.event.api.topic.Restaurant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantSerdeTest {

    RestaurantSerde restaurantSerde = new RestaurantSerde();

    @Test
    void testDeserialize(){
        String json = "{\"id\":1,\"status\":\"OPEN\"}";
        Restaurant restaurant = restaurantSerde.deserializer().deserialize("", json.getBytes());
        Assertions.assertEquals(1L, restaurant.getId());
    }

    @Test
    void testSerialize(){
        String json = "{\"id\":1,\"status\":\"OPEN\"}";
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setStatus(Status.OPEN);
        byte[] serialize = restaurantSerde.serializer().serialize("", restaurant);
        Assertions.assertEquals(json, new String(serialize));
    }
}
