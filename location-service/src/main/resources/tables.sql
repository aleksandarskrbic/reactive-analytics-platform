CREATE TABLE locations (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR NOT NULL,
  description VARCHAR NOT NULL
);

INSERT INTO locations("name", description )
SELECT
  'Name - ' || seq AS "name",
  'Description - ' || seq as description
FROM GENERATE_SERIES(1, 100) seq;