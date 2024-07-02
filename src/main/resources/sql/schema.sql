create table YUSER(
    id identity primary key,
    username varchar2(64) not null,
    name varchar2(64) not null,
    surname varchar2(64) not null,
    email varchar2(64) not null
);

create table TIMEOFF(
    id identity primary key,
    type enum ('FULL_DAY', 'PARTIAL_DAY') not null,
    result enum ('APPROVED', 'REJECTED', 'PENDING') not null DEFAULT 'PENDING',
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_from date not null,
    date_to date not null,
    user_id bigint not null,
    foreign key (user_id) references yuser (id)
);

create table TIME_RANGE(
    id identity primary key ,
    time_from time not null ,
    time_to time not null,
    partial_day_id bigint not null ,
    foreign key (partial_day_id) references TIMEOFF(id)
)