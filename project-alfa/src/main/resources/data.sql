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

INSERT INTO university(name) SELECT 'Казанский (Приволжский) федеральный университет'
WHERE NOT EXISTS (
  SELECT id
  FROM university
  WHERE id = 2
);

INSERT INTO institute(name, university_id)
  SELECT 'Институт физики', 2
  WHERE NOT EXISTS (
      SELECT id
      FROM institute
      WHERE id = 2
  );

INSERT INTO institute(name, university_id)
  SELECT 'Инженерный институт', 2
  WHERE NOT EXISTS (
      SELECT id
      FROM institute
      WHERE id = 3
  );

INSERT INTO institute(name, university_id)
  SELECT 'Высшая школа ИТИС', 2
  WHERE NOT EXISTS (
      SELECT id
      FROM institute
      WHERE id = 4
  );

INSERT INTO institute(name, university_id)
  SELECT 'Химический институт им. А.М.Бутлерова', 2
  WHERE NOT EXISTS (
      SELECT id
      FROM institute
      WHERE id = 5
  );

CREATE TABLE IF NOT EXISTS persistent_logins (
  username VARCHAR(64) NOT NULL,
  series VARCHAR(64) NOT NULL,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL,
  PRIMARY KEY (series)
);