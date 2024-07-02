insert into yuser (username, name, surname, email) values ( 'eoliosi', 'enrico', 'oliosi', 'enrico.oliosi@intesys.it' );

insert into TIMEOFF (type, date_from, date_to, user_id) values ('FULL_DAY', '2024-07-02', '2024-07-10', 1);

insert into TIMEOFF (type, date_from, date_to, user_id) values ('PARTIAL_DAY', '2024-01-03', '2024-07-03', 1);
insert into TIME_RANGE (time_from, time_to, partial_day_id) values ( '09:00:00', '12:00:00', 2 );