

INSERT INTO university(name)
    SELECT 'Admin University'
    WHERE NOT EXISTS(
        SELECT id
        FROM university
        WHERE id = 1
    );

INSERT INTO institute(name, university_id)
  SELECT 'Admin Institute', 1
  WHERE NOT EXISTS(
      SELECT id
      FROM institute
      WHERE id = 1
  );

INSERT INTO "user"(course, name, institute_id, university_id)
  SELECT 0, 'Administrator', 1, 1
  WHERE NOT EXISTS(
    SELECT id
    FROM "user"
    WHERE id = 1
  );

INSERT INTO userdata(login, hash_password, role, user_status, user_id)
  SELECT 'admin', '$2a$10$1jcIqkeEGVX.Lqz5zMLZFeuGQkxiwxUciBjzBleGXcnplIOn4A/4m', 'ADMIN', 'CONFIRMED', 1
  WHERE NOT EXISTS(
      SELECT id
      FROM userdata
      WHERE id = 1
  );

INSERT INTO criteria(name)
  SELECT 'лояльность'
  WHERE NOT EXISTS (
      SELECT id
      FROM criteria
      WHERE id = 1
  );

INSERT INTO criteria(name)
  SELECT 'возможность списать'
  WHERE NOT EXISTS (
      SELECT id
      FROM criteria
      WHERE id = 2
  );

INSERT INTO criteria(name)
  SELECT 'скорость сдачи'
  WHERE NOT EXISTS (
      SELECT id
      FROM criteria
      WHERE id = 3
  );

INSERT INTO criteria(name)
  SELECT 'адекватность'
  WHERE NOT EXISTS (
      SELECT id
      FROM criteria
      WHERE id = 4
  );

INSERT INTO criteria(name)
  SELECT 'легкость заданий'
  WHERE NOT EXISTS (
      SELECT id
      FROM criteria
      WHERE id = 5
  );

CREATE TABLE IF NOT EXISTS persistent_logins (
  username VARCHAR(64) NOT NULL,
  series VARCHAR(64) NOT NULL,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL,
  PRIMARY KEY (series)
);

