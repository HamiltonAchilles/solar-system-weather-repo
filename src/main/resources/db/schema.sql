create table solar_system_weather.forecast
(
    day integer not null
        constraint forecast_pk
        primary key,
    weather varchar not null
);

alter table solar_system_weather.forecast owner to phzsddttkezqre;

alter table solar_system_weather.forecast
    add triangle_perimeter double precision;

alter table solar_system_weather.forecast
    add triangle_area double precision;

alter table solar_system_weather.forecast
    add quadrilateral_area double precision;

alter table solar_system_weather.forecast
    add degrees_vulcano int;

alter table solar_system_weather.forecast
    add degrees_ferengi int;

alter table solar_system_weather.forecast
    add degrees_betasoide int;


