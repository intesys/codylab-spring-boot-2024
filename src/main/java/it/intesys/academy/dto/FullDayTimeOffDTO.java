package it.intesys.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullDayTimeOffDTO {

    private LocalDate start;
    private LocalDate end;

//    public void setFormattedStartDate() {
//       start = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    }
//
//    public void setFormattedEndDate() {
//        end = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//    }

}
