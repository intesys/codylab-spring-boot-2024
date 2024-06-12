package it.intesys.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullDayTimeOffDTO {

    private long id;

    private String from;

    private String to;

};
