package com.gg3083.tb.domain.day;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "day")
@Data
public class Day {

    @Id
    private Integer id;
    private Integer data;
    private Date date;
}
