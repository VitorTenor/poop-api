package com.vitortenorio.poop_api.api.v1.client;

import com.vitortenorio.poop_api.api.v1.model.ShitEventModel;
import com.vitortenorio.poop_api.api.v1.model.ShitterModel;
import com.vitortenorio.poop_api.api.v1.repository.ShitEventRepository;
import com.vitortenorio.poop_api.api.v1.repository.ShitterRepository;
import com.vitortenorio.poop_api.entity.ShitterRankingEntity;
import com.vitortenorio.poop_api.entity.UploadShitterImageEntity;
import com.vitortenorio.poop_api.enums.DayPeriod;
import com.vitortenorio.poop_api.exception.ShitterException;
import com.vitortenorio.poop_api.gateway.ShitterGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShitterClient implements ShitterGateway {
    private final ShitterRepository shitterRepository;
    private final ShitEventRepository shitEventRepository;

    @Override
    public void uploadImage(UploadShitterImageEntity entity) {
        log.info("Uploading image for shitter: {}", entity.shitterName());
        var shitterModel = this.findShitterByName(entity.shitterName());

        var base64 = Base64.getEncoder().encodeToString(entity.image());
        shitterModel.setImageBase64(base64);

        shitterRepository.save(shitterModel);
        log.info("Image uploaded for shitter: {}", entity.shitterName());
    }

    @Override
    public List<ShitterRankingEntity> ranking() {
        var ranking = shitterRepository.findTop3ShittersWithMostEvents();

        return ranking.stream().map( r ->
                new ShitterRankingEntity(
                        r.getName(),
                        r.getPosition(),
                        r.getPoints(),
                        r.getImageBase64())
        ).toList();
    }

    private ShitterModel findShitterByName(String name) {
        return shitterRepository.findByName(name)
                .orElseThrow(
                        () -> new ShitterException("Shitter not found")
                );
    }

    @Transactional
    public void buildAndSave(Map<String, List<LocalDateTime>> data){
        data.forEach((key, values) -> {
            var shitter = this.findByNameOrCreate(key);

            var shitEventList = new ArrayList<ShitEventModel>();

            var startDate = new AtomicReference<>(values.getFirst());

            for (LocalDateTime value : values) {
                var shitEvent = new ShitEventModel();

                if (Boolean.TRUE.equals(startDate.get().isAfter(value))) {
                    startDate.set(value);
                }

                shitEvent.setDateTime(value);
                shitEvent.setShitter(shitter);
                shitEvent.setPeriod(DayPeriod.classify(value));
                shitEventList.add(shitEvent);
            }

            shitter.setStartDate(startDate.get());
            shitterRepository.save(shitter);
            shitEventRepository.saveAll(shitEventList);
        });
    }

    private ShitterModel findByNameOrCreate(String key) {
        return shitterRepository.findByName(key)
                .orElseGet(() -> {
                    var shitter = new ShitterModel();
                    shitter.setName(key);
                    return shitterRepository.save(shitter);
                });
    }
}
