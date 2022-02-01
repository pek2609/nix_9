insert into buses
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://wroom.ru/i/cars2/mercedesbenz_sprinterclassic_1.jpg',
        'Mercedes-Benz Sprinter', 21);
insert into buses
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        'https://www.mantruckandbus.com/fileadmin/media/bilder/Unternehmensbereich/02_Ankara_Standorte_Produkte_865x650_1.jpg',
        'MAN', 53);
insert into buses
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        'https://www.gruzovik.com/img/auto/XXL/4460/4460_4747068240112.jpg', 'Setra S 516', 51);
insert into buses
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        'https://avto-russia.ru/autos/neoplan/photo/neoplan_skyliner_1024x768.jpg', 'Neoplan Skyliner', 96);
insert into buses
values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),
        'https://amacoint.com/wp-content/uploads/2018/04/iveco-daily-microavtobus-10.jpg', 'Iveco Daily', 27);


insert into promotions
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-03-01 00:00:00.000000',
        'https://jooinn.com/images/winter-road-9.jpg', 'Winter Sale', 30,
        '2022-12-01 00:00:00.000000');
insert into promotions
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-09-01 00:00:00.000000',
        'https://adventuretogether.com/wp-content/uploads/2017/07/Scenic-CA-drive-1024x683.jpg', 'Summer Sale', 50,
        '2022-06-01 00:00:00.000000');


insert into routs
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KIEV', 'ODESSA');
insert into routs
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ZAPOROZHYE', 'KIEV');
insert into routs
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ZAPOROZHYE', 'KHARKIV');
insert into routs
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KIEV', 'ZAPOROZHYE');
insert into routs
values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ODESSA', 'KIEV');
insert into routs
values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ODESSA', 'KHARKIV');
insert into routs
values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KHARKIV', 'ZAPOROZHYE');
insert into routs
values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KHARKIV', 'KIEV');
insert into routs
values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KHARKIV', 'ODESSA');
insert into routs
values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KIEV', 'LVIV');
insert into routs
values (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'LVIV', 'KIEV');
insert into routs
values (12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KIEV', 'KHARKIV');

insert into trips
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-01-28 06:00:00.000000', '2022-01-27 18:00:00.000000', 500, 4,
        1, 1);
insert into trips
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-01 09:00:00.000000', '2022-01-31 21:30:00.000000', 550, 3,
        null, 1);
insert into trips
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-04 08:00:00.000000', '2022-02-03 19:00:00.000000', 450, 5,
        null, 1);
insert into trips
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-01-29 09:00:00.000000', '2022-01-28 22:00:00.000000', 500, 4,
        1, 5);
insert into trips
values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-05 06:00:00.000000', '2022-02-04 18:00:00.000000', 450, 4,
        null, 5);
insert into trips
values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-01-28 14:00:00.000000', '2022-01-28 09:00:00.000000', 300, 1,
        null, 7);
insert into trips
values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-01-29 04:00:00.000000', '2022-01-28 23:00:00.000000', 275, 3,
        null, 7);
insert into trips
values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-01-28 17:00:00.000000', '2022-01-28 12:00:00.000000', 330, 1,
        null, 3);
insert into trips
values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-01-28 22:00:00.000000', '2022-01-28 17:00:00.000000', 300, 1,
        null, 3);
insert into trips
values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-01-29 23:45:00.000000', '2022-01-29 16:45:00.000000', 400,
        2, 1, 8);
insert into trips
values (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-01-30 06:30:00.000000', '2022-01-29 23:30:00.000000', 400,
        3, null, 8);
insert into trips
values (12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-01-29 23:45:00.000000', '2022-01-29 16:45:00.000000', 400,
        2, 1, 12);
insert into trips
values (13, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-01-30 06:30:00.000000', '2022-01-29 23:30:00.000000', 400,
        3, null, 12);


