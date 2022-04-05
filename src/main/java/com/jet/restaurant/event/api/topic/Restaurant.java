package com.jet.restaurant.event.api.topic;

import com.jet.restaurant.event.api.domain.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Restaurant {
    @NotNull
    private Long id;

    @NotNull
    private Status status;
}
