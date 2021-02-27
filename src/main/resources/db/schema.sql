create table solar_system_weather.forecast
(
    day integer not null
        constraint forecast_pk
        primary key,
    weather varchar not null
);

alter table solar_system_weather.forecast owner to phzsddttkezqre;


create table solar_system_weather.forecast_season
(
    id serial
        constraint forecast_season_pk
            primary key,
    start_day integer not null,
    end_day integer not null,
    peak_rainy_day integer not null,
    weather varchar not null
);

alter table solar_system_weather.forecast_season owner to phzsddttkezqre;

