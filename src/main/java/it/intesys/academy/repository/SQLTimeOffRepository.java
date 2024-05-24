package it.intesys.academy.repository;

import it.intesys.academy.model.FullDayTimeOffRequest;
import it.intesys.academy.model.PartialDayTimeOffRequest;
import it.intesys.academy.model.TimeOffRequest;
import it.intesys.academy.model.TimeRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SQLTimeOffRepository implements TimeOffRepository {

    private static final Logger log = LoggerFactory.getLogger(SQLTimeOffRepository.class);

    private final DataSource dataSource;

    public SQLTimeOffRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<TimeOffRequest> getAllTimeOffRequestsFor(Long userId) {
        List<TimeOffRequest> timeOffRequests = new ArrayList<>();
        List<TimeRange> times = new ArrayList<>(List.of(
                new TimeRange(LocalTime.of(8, 0, 0), LocalTime.of(10, 0, 0)),
                new TimeRange(LocalTime.of(17, 0, 0), LocalTime.of(18, 0, 0))
        ));

        timeOffRequests.add(
                new FullDayTimeOffRequest(LocalDate.of(2024, 6, 8), LocalDate.of(2024, 6, 12))
        );
        timeOffRequests.add(
                new FullDayTimeOffRequest(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 2))
        );
        timeOffRequests.add(
                new PartialDayTimeOffRequest(LocalDate.of(2024, 6, 8), times)
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
                    "SELECT id, available_time_off FROM yuser WHERE id = ?"
            );

            psProject.setObject(1, userId);
            resultSet = psProject.executeQuery();

            while (resultSet.next()) {
                availableTimeOff += resultSet.getLong("available_time_off");
                break;
            }

        }catch (SQLException e) {
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
