drop table if exists record;
drop table if exists classes;
drop table if exists students;

create table classes
(
    id           bigint auto_increment
        primary key,
    created      datetime(6)  null,
    updated      datetime(6)  null,
    group_name   varchar(255) not null,
    teacher_name varchar(255) not null,
    course       enum ('JAVA','JS','KOTLIN', 'PYTHON','QA'),
    unique (group_name, teacher_name, course)
);

create table students
(
    id           bigint auto_increment
        primary key,
    created      datetime(6)  null,
    updated      datetime(6)  null,
    first_name   varchar(255) not null,
    last_name    varchar(255) not null,
    age          int          not null,
    phone_number varchar(13)  not null,
    unique (phone_number),
    email        varchar(255) not null,
    unique (email)
);

create table record
(
    id         bigint auto_increment
        primary key,
    created    datetime(6) null,
    updated    datetime(6) null,
    group_id   bigint      not null,
    student_id bigint      not null,
    UNIQUE KEY `uniq_id` (`group_id`, `student_id`),
    foreign key (group_id) references classes (id),
    foreign key (student_id) references students (id)
);