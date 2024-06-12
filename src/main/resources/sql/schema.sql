create table yuser(
                      id identity primary key,
                      username varchar2(64) not null,
                      name varchar2(64) not null,
                      surname varchar2(64) not null,
                      email varchar2(64) not null
);

create table full_day_timeoff(
                                 id identity primary key,
                                 date_from date not null,
                                 date_to date not null,
                                 user_id bigint not null,
                                 foreign key (user_id) references yuser(id)
);

create table partial_day_timeoff(
                                    id identity primary key ,
                                    date date not null ,
                                    user_id bigint not null ,
                                    foreign key (user_id) references yuser(id)
);

create table time_range(
                           id identity primary key ,
                           time_from time not null ,
                           time_to time not null,
                           partial_day_id bigint not null ,
                           foreign key (partial_day_id) references partial_day_timeoff(id)
)
