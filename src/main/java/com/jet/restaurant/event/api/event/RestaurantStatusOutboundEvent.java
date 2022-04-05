package com.jet.restaurant.event.api.event;

import com.jet.restaurant.event.api.topic.Restaurant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantStatusOutboundEvent {

    private final KafkaTemplate kafkaTemplate;

    private final StreamsBuilderFactoryBean factoryBean;

    @Value("${kafka.topic.restaurant.status-update:UPDATE_STATUS}")
    private String topicName;

    @Value("${kafka.topic.restaurant.storename:status-store}")
    private String storeName;

    public void sendEvent(Restaurant restaurant) {
        kafkaTemplate.send(topicName, restaurant);
    }

    public String getRestaurantStatus(Long restaurantId) {
        KafkaStreams kafkaStreams =  factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<Long, String> store = kafkaStreams.store(StoreQueryParameters.fromNameAndType(storeName, QueryableStoreTypes.keyValueStore()));
        return store.get(restaurantId);
    }

}
