CREATE TABLE IF NOT EXISTS trip (
                                    id SERIAL PRIMARY KEY,
                                    age INTEGER,
                                    destinations TEXT[],
                                    first_dates DATE[],
                                    second_dates DATE[],
                                    third_dates DATE[],
                                    fourth_dates DATE[],
                                    fifth_dates DATE[],
                                    hobbies TEXT[],
                                    dietary_restrictions TEXT[],
                                    transport_preferences TEXT[],
                                    extra_data JSONB
);

INSERT INTO trip (
    age, destinations, first_dates, second_dates, third_dates, fourth_dates, fifth_dates,
    hobbies, dietary_restrictions, transport_preferences, extra_data
) VALUES
      (
          25,
          ARRAY['Tokyo', 'Bangkok'],
          ARRAY['2025-06-01', '2025-06-14'],
          ARRAY[]::DATE[],
          ARRAY[]::DATE[],
          ARRAY[]::DATE[],
          ARRAY[]::DATE[],
          ARRAY['Food', 'Culture'],
          ARRAY['Vegan'],
          ARRAY['Train'],
          '{}'::jsonb
      ),
      (
          40,
          ARRAY['Rome', 'Florence', 'Venice'],
          ARRAY['2025-07-01', '2025-07-05'],
          ARRAY['2025-07-06', '2025-07-08'],
          ARRAY['2025-07-09', '2025-07-11'],
          ARRAY[]::DATE[],
          ARRAY[]::DATE[],
          ARRAY['History', 'Food', 'Museums'],
          ARRAY['Vegetarian'],
          ARRAY['Train', 'Bus'],
          '{}'::jsonb
      ),
      (
          29,
          ARRAY['Lima', 'Cusco', 'La Paz', 'Quito', 'Bogotá'],
          ARRAY['2025-08-01', '2025-08-04'],
          ARRAY['2025-08-05', '2025-08-08'],
          ARRAY['2025-08-09', '2025-08-12'],
          ARRAY['2025-08-13', '2025-08-15'],
          ARRAY['2025-08-16', '2025-08-19'],
          ARRAY['Adventure', 'Hiking', 'Culture'],
          ARRAY['None'],
          ARRAY['Plane', 'Bus'],
          '{}'::jsonb
      );