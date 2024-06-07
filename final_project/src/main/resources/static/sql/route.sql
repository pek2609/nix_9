# ROUTE
INSERT INTO route (created, updated, name, description, image_path)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Kyiv to Lviv', 'This route covers major cities from Kyiv to Lviv.',
        '/image/route/lviv.jpg'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Lviv to Kyiv', 'Reverse route from Lviv back to Kyiv.',
        '/image/route/kyiv_lviv.jpg'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Dnipro to Chernivtsi',
        'This route traverses from Dnipro through Kyiv to Chernivtsi.', '/image/route/cher.jpg'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Chernivtsi to Dnipro', 'Reverse route from Chernivtsi back to Dnipro.',
        '/image/route/dnipro.jpg'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Kyiv to Kharkiv via Poltava',
        'Route from Kyiv through Poltava to Kharkiv.', '/image/route/kh.jpg'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Kharkiv to Kyiv via Poltava',
        'Reverse route from Kharkiv through Poltava back to Kyiv.', '/image/route/kyiv2.jpg'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Kyiv to Khmelnytskyi via Vinnytsya',
        'Route from Kyiv through Vinnytsya to Khmelnytskyi.', '/image/route/khm.jpg'),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Khmelnytskyi to Kyiv via Vinnytsya',
        'Reverse route from Khmelnytskyi through Vinnytsya back to Kyiv.', '/image/route/kiev.jpg');


#ROUTE_ELEMENT
-- Kyiv to Lviv and Lviv to Kyiv
INSERT INTO route_element (created, updated, route_id, town_id)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 3),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 13),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 10),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 7),

       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 7),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 10),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 13),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 3);

-- Dnipro to Chernivtsi and Chernivtsi to Dnipro
INSERT INTO route_element (created, updated, route_id, town_id)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 5),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 1),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 2),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 3),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 12),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 11),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3, 6),

       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 6),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 11),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 12),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 3),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 2),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 1),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4, 5);

-- Kyiv to Kharkiv and Kharkiv to Kyiv
INSERT INTO route_element (created, updated, route_id, town_id)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 3),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 2),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5, 1),

       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 1),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 2),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 6, 3);

-- Kyiv to Khmelnytskyi and Khmelnytskyi to Kyiv
INSERT INTO route_element (created, updated, route_id, town_id)
VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 3),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 12),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 7, 11),

       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, 11),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, 12),
       (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 8, 3);
