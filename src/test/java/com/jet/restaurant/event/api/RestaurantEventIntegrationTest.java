package com.jet.restaurant.event.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jet.restaurant.event.api.domain.Status;
import com.jet.restaurant.event.api.topic.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class RestaurantEventIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void updateRestaurantStatusTest() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setStatus(Status.OPEN);
        mockMvc.perform(post("/restaurant-event/update-status").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(restaurant))).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}