package com.jet.restaurant.event.api.controller;

import com.jet.restaurant.event.api.domain.Status;
import com.jet.restaurant.event.api.event.RestaurantStatusOutboundEvent;
import com.jet.restaurant.event.api.topic.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class RestaurantEventControllerTest {

    @Autowired
    private RestaurantEventController controller;

    @MockBean
    private RestaurantStatusOutboundEvent event;

    void testUpdateRestaurantStatus() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setStatus(Status.OPEN);
        doNothing().when(event).sendEvent(restaurant);
        assertNotNull(controller.updateRestaurantStatus(restaurant));
    }

    @Test
    void testGetStatus() {
        Long restaurantId = 1L;
        String status = "OPEN";
        when(event.getRestaurantStatus(restaurantId)).thenReturn(status);
        ResponseEntity<String> response = controller.getStatus(restaurantId);
        assertEquals(status, response.getBody());
    }
}
