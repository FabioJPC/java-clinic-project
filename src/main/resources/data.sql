-- Monday
INSERT INTO clinic_configs (day_of_week, open_time, close_time, break_start, break_end, is_half_day, is_open)
VALUES ('MONDAY', '08:00:00', '18:00:00', '12:00:00', '13:00:00', false, true);

-- Tuesday
INSERT INTO clinic_configs (day_of_week, open_time, close_time, break_start, break_end, is_half_day, is_open)
VALUES ('TUESDAY', '08:00:00', '18:00:00', '12:00:00', '13:00:00', false, true);

-- Wednesday
INSERT INTO clinic_configs (day_of_week, open_time, close_time, break_start, break_end, is_half_day, is_open)
VALUES ('WEDNESDAY', '08:00:00', '18:00:00', '12:00:00', '13:00:00', false, true);

-- Thursday
INSERT INTO clinic_configs (day_of_week, open_time, close_time, break_start, break_end, is_half_day, is_open)
VALUES ('THURSDAY', '08:00:00', '18:00:00', '12:00:00', '13:00:00', false, true);

-- Friday
INSERT INTO clinic_configs (day_of_week, open_time, close_time, break_start, break_end, is_half_day, is_open)
VALUES ('FRIDAY', '08:00:00', '18:00:00', '12:00:00', '13:00:00', false, true);

-- Saturday
INSERT INTO clinic_configs (day_of_week, open_time, close_time, is_half_day, is_open)
VALUES ('SATURDAY', '08:00:00', '12:00:00', true, true);

-- Sunday
INSERT INTO clinic_configs (day_of_week, is_open)
VALUES ('SUNDAY', false);