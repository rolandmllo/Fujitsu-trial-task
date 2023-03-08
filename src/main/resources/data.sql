INSERT INTO vehicle (vehicle_type) VALUES ('Car');
INSERT INTO vehicle (vehicle_type) VALUES ('Scooter');
INSERT INTO vehicle (vehicle_type) VALUES ('Bike');


INSERT INTO city (city_name, weather_observation_station) VALUES ('Tallinn', 'Tallinn-Harku');
INSERT INTO city (city_name, weather_observation_station) VALUES ('Tartu', 'Tartu-Tõravere');
INSERT INTO city (city_name, weather_observation_station) VALUES ('Pärnu', 'Pärnu');


INSERT INTO regional_base_fee (city_id, vehicle_id, regional_base_fee)
VALUES ((SELECT id FROM city WHERE city_name = 'Tallinn'),
        (SELECT id FROM vehicle WHERE vehicle_type = 'Car'),
        4);

INSERT INTO regional_base_fee (city_id, vehicle_id, regional_base_fee)
VALUES ((SELECT id FROM city WHERE city_name = 'Tallinn'),
        (SELECT id FROM vehicle WHERE vehicle_type = 'Scooter'),
        3.5);

INSERT INTO regional_base_fee (city_id, vehicle_id, regional_base_fee)
VALUES ((SELECT id FROM city WHERE city_name = 'Tallinn'),
        (SELECT id FROM vehicle WHERE vehicle_type = 'Bike'),
        3);


INSERT INTO regional_base_fee (city_id, vehicle_id, regional_base_fee)
VALUES ((SELECT id FROM city WHERE city_name = 'Tartu'),
        (SELECT id FROM vehicle WHERE vehicle_type = 'Car'),
        3.5);

INSERT INTO regional_base_fee (city_id, vehicle_id, regional_base_fee)
VALUES ((SELECT id FROM city WHERE city_name = 'Tartu'),
        (SELECT id FROM vehicle WHERE vehicle_type = 'Scooter'),
        3);


INSERT INTO regional_base_fee (city_id, vehicle_id, regional_base_fee)
VALUES ((SELECT id FROM city WHERE city_name = 'Tartu'),
        (SELECT id FROM vehicle WHERE vehicle_type = 'Bike'),
        2.5);


INSERT INTO regional_base_fee (city_id, vehicle_id, regional_base_fee)
VALUES ((SELECT id FROM city WHERE city_name = 'Pärnu'),
        (SELECT id FROM vehicle WHERE vehicle_type = 'Car'),
        3);

INSERT INTO regional_base_fee (city_id, vehicle_id, regional_base_fee)
VALUES ((SELECT id FROM city WHERE city_name = 'Pärnu'),
        (SELECT id FROM vehicle WHERE vehicle_type = 'Scooter'),
        2.5);

INSERT INTO regional_base_fee (city_id, vehicle_id, regional_base_fee)
VALUES ((SELECT id FROM city WHERE city_name = 'Pärnu'),
        (SELECT id FROM vehicle WHERE vehicle_type = 'Bike'),
        2);
