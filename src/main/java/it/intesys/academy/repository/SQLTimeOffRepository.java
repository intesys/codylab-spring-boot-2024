package it.intesys.academy.repository;

import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.model.TimeRange;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLTimeOffRepository implements TimeOffRepository {

  private static final Logger log = LoggerFactory.getLogger(SQLTimeOffRepository.class);

  private final DataSource dataSource;

  public SQLTimeOffRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public List<TimeOffRequest> getAllTimeOffRequestsFor(Long userId) {
    List<TimeOffRequest> timeOffRequests = new ArrayList<>();

    timeOffRequests.add(
        new FullDayTimeOffRequest(
            LocalDate.of(2024, Month.JULY, 1),
            LocalDate.of(2024, Month.JULY, 1)
        )
    );

    timeOffRequests.add(
        new FullDayTimeOffRequest(
            LocalDate.of(2024, Month.AUGUST, 12),
            LocalDate.of(2024, Month.AUGUST, 16)
        )
    );

    timeOffRequests.add(
        new PartialDayTimeOffRequest(
            LocalDate.of(2024, Month.JUNE, 15),
            List.of(new TimeRange(LocalTime.of(12, 00), LocalTime.of(12, 30))
            )
        )
    );

    return timeOffRequests;
  }

  @Override
  public Duration getAvailableTimeOffForUser(Long userId) {

    ResultSet resultSet = null;
    ResultSet resultSetIssues = null;

    long availableTimeOff = 0;
    try (Connection conn = dataSource.getConnection()) {

      PreparedStatement psProject = conn.prepareStatement(
          "SELECT id, available_time_off from yuser where id = ?");
      psProject.setObject(1, userId);

      resultSet = psProject.executeQuery();

      while (resultSet.next()) {

        availableTimeOff = resultSet.getLong("available_time_off");
        break;
      }

    } catch (SQLException e) {
      log.error("SQLException", e);
    } catch (Exception e) {
      log.error("Exception", e);
    } finally {
      DatabaseManager.closeResultSet(resultSet);
      DatabaseManager.closeResultSet(resultSetIssues);
    }

    return Duration.ofDays(availableTimeOff);
  }
}
