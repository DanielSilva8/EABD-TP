
----------------------CREATE USER-----------------------------------------


CREATE USER TP IDENTIFIED BY oracle;

ALTER USER TP DEFAULT TABLESPACE USERS
QUOTA UNLIMITED ON USERS;


ALTER USER TP TEMPORARY TABLESPACE TEMP;

-- ------------------CREATE TABLES----------------------------------------
--
-- DBA.DBA_USERS
CREATE TABLE TP.USERS(
  user_id NUMBER PRIMARY KEY ,
  username VARCHAR(20),
  created DATE,
  status VARCHAR(32)
);

-- --DBA.DBA_TABLESPACES
CREATE TABLE TP.TABLESPACES(
  name VARCHAR(20) PRIMARY KEY ,
  status VARCHAR(10)
);

-- CREATE UNIQUE INDEX tablespace_id ON TABLESPACES (tablespace_id);
--
--DBA.DBA_DATA_FILES
CREATE TABLE TP.DATAFILES(
  datafile_id NUMBER PRIMARY KEY ,
  name VARCHAR(513),
  datafile_size NUMBER,
  status VARCHAR(9),
  tablespace_name VARCHAR(30)
);
--
-- CREATE UNIQUE INDEX datafile_id ON DATAFILES (datafile_id);
--
-- --V$SESSION;
--
CREATE TABLE TP.SESSIONS(
  session_id NUMBER PRIMARY KEY ,
  username VARCHAR2(30),
  machine VARCHAR2(64),
  status VARCHAR2(8),
  program VARCHAR(48),
  type VARCHAR2(10),
  schema VARCHAR2(30)
);
--
-- CREATE UNIQUE INDEX session_id ON SESSIONS (session_id);
--
-- --v$parameter;
--
CREATE TABLE TP.STATS(

  stat_id NUMBER PRIMARY KEY,
  name VARCHAR2(64),
  stat_value  NUMBER
);

-- CREATE UNIQUE INDEX stat_id ON STATS (stat_id);

----------------------PERMISSIONS-----------------------------------------

GRANT CONNECT TO TP;
GRANT CREATE SESSION TO TP;
GRANT SELECT ON TP.USERS TO TP;
GRANT SELECT ON TP.DATAFILES TO TP;
GRANT SELECT ON TP.TABLESPACES TO TP;
GRANT SELECT ON TP.STATS TO TP;


--TODO---------------------TRIGGERS----------------------------------------