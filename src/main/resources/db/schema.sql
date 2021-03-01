create table solar_system_weather.forecast
(
    day     integer not null
        constraint forecast_pk
            primary key,
    weather varchar not null
);

alter table solar_system_weather.forecast
    owner to phzsddttkezqre;

alter table solar_system_weather.forecast
    add triangle_perimeter integer;

alter table solar_system_weather.forecast
    add triangle_area integer;

alter table solar_system_weather.forecast
    add degrees_vulcano integer;

alter table solar_system_weather.forecast
    add degrees_ferengi integer;

alter table solar_system_weather.forecast
    add degrees_betasoide integer;


