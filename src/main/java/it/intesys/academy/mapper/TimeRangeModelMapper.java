//package it.intesys.academy.mapper;
//
//import it.intesys.academy.dto.TimeRangeDTO;
//import it.intesys.academy.model.TimeRange;
//
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class TimeRangeModelMapper {
//
//    private static TimeRange fromDTOtoEntity(TimeRangeDTO timeRangeDTO) {
//
//        TimeRange timeRange = ModelMapperUtils.modelMapper.map(timeRangeDTO, TimeRange.class);
//
//        timeRange.setFrom(LocalTime.parse(timeRangeDTO.getFrom(), DateTimeFormatter.ofPattern("HH:mm")));
//        timeRange.setTo(LocalTime.parse(timeRangeDTO.getTo(), DateTimeFormatter.ofPattern("HH:mm")));
//
//        return timeRange;
//    }
//
//    public static List<TimeRange> fromDTOstoEntities(List<TimeRangeDTO> timeRangeDTOs, PartialDayTimeOff partialDayTimeOff) {
//
//        List<TimeRange> timeRanges = timeRangeDTOs.stream().map(TimeRangeModelMapper::fromDTOtoEntity).collect(Collectors.toList());
//        timeRanges.forEach(timeRange -> timeRange.setPartialDayTimeOff(partialDayTimeOff));
//
//        return timeRanges;
//
//    }
//
//}
