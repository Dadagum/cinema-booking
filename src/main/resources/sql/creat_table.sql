# film table

CREATE TABLE film (
  fid INT AUTO_INCREMENT,
  name VARCHAR(30),
  director VARCHAR (30),
  actors VARCHAR (100),
  price DOUBLE ,
  last_len DOUBLE ,
  type VARCHAR(30),
  introduction VARCHAR(800),
  PRIMARY KEY (fid)
)ENGINE=INNODB

# film_period table

CREATE TABLE film_period (
  fid INT ,
  pid INT AUTO_INCREMENT,
  start_time TIMESTAMP ,
  video_hall INT ,
  PRIMARY KEY (pid) ,
  FOREIGN KEY (fid) REFERENCES film(fid)
)ENGINE=INNODB

# order_ table

CREATE TABLE order_(
  oid INT AUTO_INCREMENT,
  pid INT ,
  PRIMARY KEY (oid),
  FOREIGN KEY (pid) REFERENCES film_period(pid)
)ENGINE=INNODB

# order_seat table

CREATE TABLE order_seat(
  oid INT ,
  num INT ,
  FOREIGN KEY (oid) REFERENCES order_(oid)
)ENGINE=INNODB