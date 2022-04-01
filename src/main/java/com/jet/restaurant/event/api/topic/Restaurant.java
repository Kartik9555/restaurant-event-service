package com.jet.restaurant.event.api.topic;

import com.jet.restaurant.event.api.domain.Status;
import lombok.Data;

@Data
public class Restaurant {
    private Long id;
    private Status status;
}
