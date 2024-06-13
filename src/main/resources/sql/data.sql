insert into yuser (username, name, surname, email) values ( 'eoliosi', 'enrico', 'oliosi', 'enrico.oliosi@intesys.it' );

insert into full_day_timeoff (date_from, date_to, user_id) values ( '2024-01-01', '2024-01-08', 1);

insert into partial_day_timeoff (date, user_id) values ( '2024-01-15', 1);

insert into time_range (time_from, time_to, partial_day_id) values ( '09:00:00', '12:00:00', 1 );

insert into time_range (time_from, time_to, partial_day_id) values ( '17:00:00', '18:00:00', 1 );

insert into yuser (username, name, surname, email) values ( 'abenettoni', 'anna', 'benettoni', 'anna.benettoni@intesys.it');

insert into yuser (username, name, surname, email) values ( 'timran', 'talha', 'imran', 'talha.imran@intesys.it');


insert into partial_day_timeoff (date, user_id) values ( '2024-01-25', 1);

insert into time_range (time_from, time_to, partial_day_id) values ( '09:00:00', '10:00:00', 3 );

insert into full_day_timeoff (date_from, date_to, user_id) values ('2024-08-08', '2024-09-09', 1);

insert into full_day_timeoff (date_from, date_to, user_id) values ('2024-08-08', '2024-09-09', 1);
