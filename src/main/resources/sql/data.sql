insert into yuser (username, name, surname, email) values ( 'eoliosi', 'enrico', 'oliosi', 'enrico.oliosi@intesys.it' );

insert into full_day_timeoff (date_from, date_to, user_id) values ( '2024-01-01', '2024-01-08', 1);

insert into partial_day_timeoff (date, user_id) values ( '2024-01-15', 1);

insert into time_range (time_from, time_to, partial_day_id) values ( '09:00:00', '12:00:00', 2 );

insert into time_range (time_from, time_to, partial_day_id) values ( '17:00:00', '18:00:00', 2 );

insert into partial_day_timeoff (date, user_id) values ( '2024-08-23', 1);

insert into time_range (time_from, time_to, partial_day_id) values ( '10:00:00', '12:00:00', 3 );

insert into time_range (time_from, time_to, partial_day_id) values ( '14:00:00', '17:00:00', 3 );