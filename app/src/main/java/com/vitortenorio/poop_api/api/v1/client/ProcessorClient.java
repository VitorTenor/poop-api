package com.vitortenorio.poop_api.api.v1.client;

import com.vitortenorio.poop_api.api.v1.repository.ShitEventRepository;
import com.vitortenorio.poop_api.api.v1.repository.ShitterRepository;
import com.vitortenorio.poop_api.entity.ProcessFileEntity;
import com.vitortenorio.poop_api.gateway.ProcessorGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ProcessorClient implements ProcessorGateway {
    private final ShitterClient shitterClient;
    private final ShitEventRepository shitEventRepository;

    @Override
    public void processFile(ProcessFileEntity entity) {
        shitEventRepository.deleteAll();

        var data = new StringBuilder();

        var inputReader = new InputStreamReader(new ByteArrayInputStream(entity.file()));

        try (var br = new BufferedReader(inputReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                data.append(line);
                data.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        var resultMap = this.convertResultWithShit(data.toString(), entity.startDate());
        shitterClient.buildAndSave(resultMap);
    }

    private Map<String, List<LocalDateTime>>  convertResultWithShit(String data, LocalDate startDate) {
        var pattern =  Pattern.compile("(\\d{2}/\\d{2}/\\d{2}), (\\d{2}:\\d{2}:\\d{2})] (.*?): .*💩.*");
        var matcher = pattern.matcher(data);

        Map<String, List<LocalDateTime>>  result = new HashMap<>();
        while (matcher.find()) {
            var dateStr = matcher.group(1);
            if (!Objects.isNull(dateStr)) {
                var date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yy"));

                if (date.isAfter(startDate)) {
                    var hour = matcher.group(2);
                    var name = matcher.group(3);

                    result.computeIfAbsent(name, k -> new ArrayList<>());
                    result.get(matcher.group(3)).add(this.generateByString(date, hour));
                }

            }
        }

        return result;
    }

    private LocalDateTime generateByString(LocalDate date, String hour){
        var cleanedHour =  hour.replace('\u202F', ' ').trim();

        var formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date + " "+ cleanedHour, formatterDate);
    }
}
