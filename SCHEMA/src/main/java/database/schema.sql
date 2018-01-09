-------------------CREATE ROLE -------------------------------------------

CREATE ROLE ANALYST;
GRANT SELECT ON SYS.DBA_USERS TO ANALYST;
GRANT SELECT ON SYS.DBA_TABLESPACES TO ANALYST;
GRANT SELECT ON SYS.DBA_DATA_FILES TO ANALYST;
GRANT SELECT ON SYS.DBA_HIST_OSSTAT TO ANALYST;
GRANT SELECT ON SYS.V_$SESSION TO ANALYST;
GRANT SELECT ON SYS.V_$SQL TO ANALYST;
GRANT SELECT ON SYS.v_$sesstat TO ANALYST;
GRANT SELECT ON SYS.v_$bgprocess TO ANALYST;
GRANT SELECT ON SYS.v_$process TO ANALYST;
GRANT SELECT ON SYS.v_$instance TO ANALYST;
GRANT SELECT ON SYS.v_$statname TO ANALYST;

-------------------DATE FORMAT--------------------------------------------

ALTER SESSION SET NLS_DATE_FORMAT = 'DD-MON-YYYY HH24:MI:SS';

-------------------CREATE TABLESPACE--------------------------------------

CREATE TABLESPACE TP_TABLESPACE DATAFILE 'TP01.dbf' SIZE 100M ONLINE;


----------------------CREATE USER-----------------------------------------


CREATE USER TP IDENTIFIED BY oracle;
GRANT ANALYST TO TP;

ALTER USER TP DEFAULT TABLESPACE TP_TABLESPACE
QUOTA UNLIMITED ON TP_TABLESPACE;
ALTER USER TP TEMPORARY TABLESPACE TEMP;

-- ------------------CREATE TABLES----------------------------------------
--
-- DBA.DBA_USERS
CREATE TABLE TP.USERS(
  user_id NUMBER,
  username VARCHAR(20),
  created DATE,
  status VARCHAR(32),
  data DATE DEFAULT CURRENT_DATE,
  CONSTRAINT users_pk PRIMARY KEY (user_id, data)
);

-- --DBA.DBA_TABLESPACES
CREATE TABLE TP.TABLESPACES(
  name VARCHAR(20),
  status VARCHAR(10),
  data DATE DEFAULT CURRENT_DATE,
  CONSTRAINT tablespaces_pk PRIMARY KEY (name, data)
);


--DBA.DBA_DATA_FILES
CREATE TABLE TP.DATAFILES(
  datafile_id NUMBER,
  name VARCHAR(513),
  datafile_size NUMBER,
  status VARCHAR(9),
  tablespace_name VARCHAR(30),
  data DATE DEFAULT CURRENT_DATE,
  CONSTRAINT datafiles_pk PRIMARY KEY (datafile_id, data)
);

-- --V$SESSION;
CREATE TABLE TP.SESSIONS(
  session_id NUMBER,
  username VARCHAR2(30),
  machine VARCHAR2(64),
  status VARCHAR2(8),
  program VARCHAR(48),
  type VARCHAR2(10),
  schema VARCHAR2(30),
  data DATE DEFAULT CURRENT_DATE,
  CONSTRAINT sessions_pk PRIMARY KEY (session_id, data)
);

-- --v$parameter;

CREATE TABLE TP.STATS(
  stat_name VARCHAR2(64) NULL,
  stat_value  NUMBER NULL,
  data DATE DEFAULT CURRENT_DATE,
  CONSTRAINT stats_pk PRIMARY KEY (stat_name, data)
);

CREATE TABLE TP.MEMORY(
  sid NUMBER,
  current_size FLOAT,
  maximum_size  FLOAT,
  data DATE DEFAULT CURRENT_DATE,
  CONSTRAINT memory_pk PRIMARY KEY (sid, data)
);

----------------------CREATE VIEWS----------------------------------------

CREATE OR REPLACE VIEW TP.USERS_VIEW AS
SELECT user_id, username, created, status
FROM users
WHERE NOT data < (SELECT MAX(data - 3/86400) FROM users);

CREATE OR REPLACE VIEW TP.TABLESPACES_VIEW AS
SELECT name, status
FROM tablespaces
WHERE NOT data < (SELECT MAX(data - 3/86400) FROM tablespaces);

CREATE OR REPLACE VIEW TP.DATAFILES_VIEW AS
SELECT name, datafile_size, status, tablespace_name
FROM datafiles
WHERE NOT data < (SELECT MAX(data - 3/86400) FROM datafiles);

CREATE OR REPLACE VIEW TP.SESSIONS_VIEW AS
SELECT session_id, username, machine, status, program, type, schema
FROM sessions
WHERE NOT data < (SELECT MAX(data - 3/86400) FROM sessions);

CREATE OR REPLACE VIEW TP.MEMORY_VIEW AS
SELECT sid, current_size, maximum_size AS MAXIMUMSIZE
FROM memory
WHERE NOT data < (SELECT MAX(data - 3/86400) FROM memory);

CREATE OR REPLACE VIEW TP.STATS_VIEW AS
SELECT stat_name AS STAT_NAME, stat_value AS STAT_VALUE
FROM stats
WHERE NOT data < (SELECT MAX(data - 3/86400) FROM stats);

----------------------PERMISSIONS-----------------------------------------

GRANT CONNECT TO TP;
GRANT CREATE SESSION TO TP;
