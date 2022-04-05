package com.jet.restaurant.event.api.component;

import com.jet.restaurant.event.api.serde.RestaurantSerde;
import com.jet.restaurant.event.api.topic.Restaurant;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestaurantStatusProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<Restaurant> RESTAURANT_SERDE = new RestaurantSerde();

    @Value("${kafka.topic.restaurant.status-update:UPDATE_STATUS}")
    private String topicName;

    @Value("${kafka.topic.restaurant.storename:status-store}")
    private String storeName;

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, Restaurant> messageStream = streamsBuilder
                .stream(topicName, Consumed.with(STRING_SERDE, RESTAURANT_SERDE));

        KeyValueMapper<String, Restaurant, KeyValue<Long,String>> restIdStatusMapper = (k, v) -> new KeyValue<>(v.getId(), v.getStatus().name());
        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(storeName);
        Materialized<Long, String, KeyValueStore<Bytes, byte[]>> idStatusKeyValueStore = Materialized.<Long, String>as(storeSupplier).withKeySerde(Serdes.Long()).withValueSerde(Serdes.String());
        messageStream.map(restIdStatusMapper).toTable(idStatusKeyValueStore);
    }
}
