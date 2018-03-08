INSERT INTO university(id, name)
    SELECT 1, 'Admin University'
    WHERE NOT EXISTS(
        SELECT id
        FROM university
        WHERE id = 1
    );

INSERT INTO institute(id, name, university_id)
  SELECT 1, 'Admin Institute', 1
  WHERE NOT EXISTS(
      SELECT id
      FROM institute
      WHERE id = 1
  );

INSERT INTO "user"(id, course, name, institute_id, university_id)
  SELECT 1, 0, 'Administrator', 1, 1
  WHERE NOT EXISTS(
    SELECT id
    FROM "user"
    WHERE id = 1
  );

INSERT INTO userdata(id, login, hash_password, role, user_status, user_id)
  SELECT 1, 'admin', '$2a$10$1jcIqkeEGVX.Lqz5zMLZFeuGQkxiwxUciBjzBleGXcnplIOn4A/4m', 'ADMIN', 'CONFIRMED', 1
  WHERE NOT EXISTS(
      SELECT id
      FROM userdata
      WHERE id = 1
  );

CREATE TABLE IF NOT EXISTS persistent_logins (
  username VARCHAR(64) NOT NULL,
  series VARCHAR(64) NOT NULL,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL,
  PRIMARY KEY (series)
);