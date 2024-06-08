package ua.com.alevel.service.mail;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class EmailParameters {

    private String from;
    private String to;
    private String subject;
    private String message;
}
