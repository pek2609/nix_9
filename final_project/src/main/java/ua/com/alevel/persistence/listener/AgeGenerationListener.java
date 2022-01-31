package ua.com.alevel.persistence.listener;

import org.apache.commons.lang3.StringUtils;
import ua.com.alevel.persistence.entity.user.Client;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AgeGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateAge(Client personal) {
        if (personal.getBirthDate() != null) {
            personal.setAge((int) ChronoUnit.YEARS.between(LocalDate.now(), personal.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
        }
    }
}
