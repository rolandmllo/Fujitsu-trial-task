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



INSERT INTO air_temperature_extra_fee (vehicle_id, lower_temp, higher_temp, forbidden, air_temperature_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Scooter'),
        -99, -10.1, false, 1);

INSERT INTO air_temperature_extra_fee (vehicle_id, lower_temp, higher_temp, forbidden, air_temperature_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Bike'),
        -99, -10.1, false, 1);

INSERT INTO air_temperature_extra_fee (vehicle_id, lower_temp, higher_temp, forbidden, air_temperature_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Scooter'),
        -10, 0, false, 0.5);

INSERT INTO air_temperature_extra_fee (vehicle_id, lower_temp, higher_temp, forbidden, air_temperature_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Bike'),
        -10, 0, false, 0.5);



INSERT INTO wind_speed_extra_fee (vehicle_id, lower_wind_speed, higher_wind_speed, forbidden, wind_speed_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Bike'),
        10, 20, false, 0.5);


INSERT INTO wind_speed_extra_fee (vehicle_id, lower_wind_speed, higher_wind_speed, forbidden, wind_speed_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Bike'),
        20.1, 200, true, 0);



INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Bike'),
        'sleet', false, 1);

INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'scooter'),
        'sleet', false, 1);

INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Bike'),
        'snow', false, 1);

INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'scooter'),
        'snow', false, 1);

INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Bike'),
        'rain', false, 0.5);

INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'scooter'),
        'rain', false, 0.5);


INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Bike'),
        'glaze', true, 0);

INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'scooter'),
        'glaze', true, 0);

INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Bike'),
        'hail', true, 0);

INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'scooter'),
        'hail', true, 0);

INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'Bike'),
        'thunder', true, 0);

INSERT INTO weather_phenomenon_extra_fee (vehicle_id, weather_phenomenon, forbidden, weather_phenomenon_extra_fee)
values ((SELECT id FROM  vehicle WHERE vehicle_type = 'scooter'),
        'thunder', true, 0);





