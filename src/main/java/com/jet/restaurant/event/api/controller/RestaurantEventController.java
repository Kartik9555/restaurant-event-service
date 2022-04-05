package com.jet.restaurant.event.api.controller;

import com.jet.restaurant.event.api.event.RestaurantStatusOutboundEvent;
import com.jet.restaurant.event.api.topic.Restaurant;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurant-event")
@Api(value = "Restaurant Event", tags = {"Restaurant Event"})
public class RestaurantEventController {

    private final RestaurantStatusOutboundEvent event;

    @PostMapping("update-status")
    public ResponseEntity<String> updateRestaurantStatus(@RequestBody @Valid Restaurant restaurant){
        event.sendEvent(restaurant);
        return ResponseEntity.ok("CREATED");
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<String> getStatus(@PathVariable Long restaurantId){
        return ResponseEntity.ok(event.getRestaurantStatus(restaurantId));
    }
}
