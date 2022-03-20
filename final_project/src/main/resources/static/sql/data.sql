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
        '2021-12-01 00:00:00.000000');
insert into promotions
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-09-01 00:00:00.000000',
        'https://adventuretogether.com/wp-content/uploads/2017/07/Scenic-CA-drive-1024x683.jpg', 'Summer Sale', 50,
        '2022-06-01 00:00:00.000000');
insert into promotions
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-03-01 00:00:00.000000',
        'https://clipart.world/wp-content/uploads/2020/08/february-month-name-png.png', 'Sweet February', 15,
        '2022-02-01 00:00:00.000000');
insert into promotions
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-25 00:00:00.000000',
        'https://www.winstar.com.tw/uploads/photos/special-offer.jpg', 'Special Sale', 25,
        '2022-02-05 00:00:00.000000');


insert into routes
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KIEV', 'ODESSA');
insert into routes
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ZAPOROZHYE', 'KIEV');
insert into routes
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ZAPOROZHYE', 'KHARKIV');
insert into routes
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KIEV', 'ZAPOROZHYE');
insert into routes
values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ODESSA', 'KIEV');
insert into routes
values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ODESSA', 'KHARKIV');
insert into routes
values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KHARKIV', 'ZAPOROZHYE');
insert into routes
values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KHARKIV', 'KIEV');
insert into routes
values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KHARKIV', 'ODESSA');
insert into routes
values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KIEV', 'LVIV');
insert into routes
values (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'LVIV', 'KIEV');
insert into routes
values (12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'KIEV', 'KHARKIV');

insert into trips
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-14 06:00:00.000000', '2022-02-13 18:00:00.000000', 500, 4,
        1, 1);
insert into trips
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-16 09:00:00.000000', '2022-02-15 21:30:00.000000', 550, 3,
        null, 1);
insert into trips
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-11 08:00:00.000000', '2022-02-10 19:00:00.000000', 450, 5,
        null, 1);
insert into trips
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-16 09:00:00.000000', '2022-02-15 22:00:00.000000', 500, 4,
        1, 5);
insert into trips
values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-18 06:00:00.000000', '2022-02-17 18:00:00.000000', 450, 4,
        null, 5);
insert into trips
values (6, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-16 14:00:00.000000', '2022-02-16 09:00:00.000000', 300, 1,
        null, 7);
insert into trips
values (7, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-18 04:00:00.000000', '2022-02-17 23:00:00.000000', 275, 3,
        null, 7);
insert into trips
values (8, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-15 17:00:00.000000', '2022-02-14 12:00:00.000000', 330, 1,
        null, 3);
insert into trips
values (9, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-15 22:00:00.000000', '2022-02-14 17:00:00.000000', 300, 1,
        null, 3);
insert into trips
values (10, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-16 23:45:00.000000', '2022-02-16 16:45:00.000000', 400,
        2, 1, 8);
insert into trips
values (11, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-17 06:30:00.000000', '2022-02-16 23:30:00.000000', 400,
        3, null, 8);
insert into trips
values (12, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-17 23:45:00.000000', '2022-02-17 16:45:00.000000', 400,
        2, 1, 12);
insert into trips
values (13, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), '2022-02-18 06:30:00.000000', '2022-02-17 23:30:00.000000', 400,
        3, null, 12);

insert into orders
values (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 0, 300, 'Andrew', 'Marlyn', '0771234567', null, 6);
insert into orders
values (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 1, 675, 'Lida', 'Razhnikova', '0843215782', null, 5);
insert into orders
values (3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 0, 467.5, 'Ilya', 'Pekaruk', '+380660235493', 2, 2);
insert into orders
values (4, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 0, 280, 'Adrian', 'Lewis', '0771234567', null, 10);
insert into orders
values (5, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1, 0, 275, 'Ilya', 'Pekaruk', '380660235493', 2, 7);


