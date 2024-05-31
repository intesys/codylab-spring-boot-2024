insert into yuser (username, name, surname, email) values ( 'eoliosi', 'enrico', 'oliosi', 'enrico.oliosi@intesys.it' );

insert into full_day_timeoff (date_from, date_to, user_id) values ( '2024-01-01', '2024-01-08', 1);

insert into partial_day_timeoff (date, user_id) values ( '2024-01-15', 1);

insert into time_range (time_from, time_to, partial_day_id) values ( '09:00:00', '12:00:00', 1 );

insert into time_range (time_from, time_to, partial_day_id) values ( '17:00:00', '18:00:00', 1 );

insert into yuser (username, name, surname, email) values ( 'pfornale', 'pietro', 'fornale', 'pietro.fornale@intesys.it' );

insert into full_day_timeoff (date_from, date_to, user_id) values ( '2024-01-03', '2024-01-09', 2);

insert into partial_day_timeoff (date, user_id) values ( '2024-03-16', 2);

insert into time_range (time_from, time_to, partial_day_id) values ( '11:00:00', '12:00:00', 2 );

insert into time_range (time_from, time_to, partial_day_id) values ( '16:00:00', '18:00:00', 2 );

insert into yuser (username, name, surname, email) values ( 'timran', 'talha', 'imran', 'talha.imran@intesys.it' );

insert into full_day_timeoff (date_from, date_to, user_id) values ( '2024-04-15', '2024-04-18', 3);

insert into partial_day_timeoff (date, user_id) values ( '2024-11-19', 3);

insert into time_range (time_from, time_to, partial_day_id) values ( '10:00:00', '12:30:00', 3 );

insert into time_range (time_from, time_to, partial_day_id) values ( '15:00:00', '16:30:00', 3 );

insert into yuser (username, name, surname, email) values ( 'abenettoni', 'anna', 'benettoni', 'anna.benettoni@intesys.it' );

insert into full_day_timeoff (date_from, date_to, user_id) values ( '2024-01-29', '2024-02-01', 4);

insert into partial_day_timeoff (date, user_id) values ( '2024-12-16', 4);

insert into time_range (time_from, time_to, partial_day_id) values ( '11:00:00', '12:00:00', 4 );

insert into time_range (time_from, time_to, partial_day_id) values ( '16:00:00', '18:00:00', 4 );