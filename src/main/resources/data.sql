INSERT INTO vehicle (id, vehicle_type) VALUES (1, 'Car');
INSERT INTO vehicle (id, vehicle_type) VALUES (2, 'Scooter');
INSERT INTO vehicle (id, vehicle_type) VALUES (3, 'Bike');


INSERT INTO city (id, city_name, weather_observation_station) VALUES (1, 'Tallinn', 'Tallinn-Harku');
INSERT INTO city (id, city_name, weather_observation_station) VALUES (2, 'Tartu', 'Tartu-Tõravere');
INSERT INTO city (id, city_name, weather_observation_station) VALUES (3, 'Pärnu', 'Pärnu');


INSERT INTO regional_base_fee (id, city_id, vehicle_id, regional_base_fee) VALUES (1, 1, 1, 4);
INSERT INTO regional_base_fee (id, city_id, vehicle_id, regional_base_fee) VALUES (2, 1, 2, 3.5);
INSERT INTO regional_base_fee (id, city_id, vehicle_id, regional_base_fee) VALUES (3, 1, 3, 3);

INSERT INTO regional_base_fee (id, city_id, vehicle_id, regional_base_fee) VALUES (4, 2, 1, 3.5);
INSERT INTO regional_base_fee (id, city_id, vehicle_id, regional_base_fee) VALUES (5, 2, 2, 3);
INSERT INTO regional_base_fee (id, city_id, vehicle_id, regional_base_fee) VALUES (6, 2, 3, 2.5);

INSERT INTO regional_base_fee (id, city_id, vehicle_id, regional_base_fee) VALUES (7, 2, 1, 3);
INSERT INTO regional_base_fee (id, city_id, vehicle_id, regional_base_fee) VALUES (8, 2, 2, 2.5);
INSERT INTO regional_base_fee (id, city_id, vehicle_id, regional_base_fee) VALUES (9, 2, 3, 2);

