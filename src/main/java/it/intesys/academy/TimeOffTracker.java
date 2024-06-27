package it.intesys.academy;

import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO;
import it.intesys.intesys.academy.dto.TimeOffRequestApiDTO.EsitoEnum;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimeOffTracker {

    public static void main(String[] args) {

        var timeoff = new TimeOffRequestApiDTO()
            .setTipologia("tipo")
            .esito(EsitoEnum.ACCEPTED)
            .orario(List.of());

        SpringApplication.run(TimeOffTracker.class, args);
    }


}
