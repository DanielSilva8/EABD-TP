

-------------------CREATE TABLESPACE------------------------------------

CREATE TABLESPACE TP_TABLESPACE DATAFILE 'TP01.dbf' SIZE 10M ONLINE;


----------------------CREATE USER-----------------------------------------


CREATE USER TP IDENTIFIED BY oracle;

ALTER USER TP DEFAULT TABLESPACE TP_TABLESPACE
QUOTA UNLIMITED ON USERS;

ALTER USER TP TEMPORARY TABLESPACE TEMP;

----------------------PERMISSIONS-----------------------------------------

GRANT CONNECT TO TP;
GRANT CREATE SESSION TO TP;
GRANT SELECT ON SYS.DBA_USERS TO TP;
GRANT SELECT ON SYS.DBA_TABLESPACES TO TP;
GRANT SELECT ON SYS.DBA_DATA_FILES TO TP;
GRANT SELECT ON SYS.DBA_HIST_OSSTAT TO TP;
GRANT SELECT ON SYS.V_$SESSION TO TP;
GRANT SELECT ON SYS.V_$SQL TO TP;

-- ------------------CREATE VIEWS----------------------------------------

CREATE OR REPLACE VIEW TP.USERS AS SELECT USER_ID, USERNAME, CREATED, ACCOUNT_STATUS FROM SYS.DBA_USERS;

CREATE OR REPLACE VIEW TP.TABLESPACES AS SELECT TABLESPACE_NAME, STATUS FROM SYS.DBA_TABLESPACES;

CREATE OR REPLACE VIEW TP.DATAFILES AS SELECT FILE_ID, FILE_NAME, BYTES, STATUS, TABLESPACE_NAME FROM SYS.DBA_DATA_FILES;

CREATE OR REPLACE VIEW TP.SESSIONS AS SELECT SID,USERNAME,MACHINE,STATUS,PROGRAM,TYPE,SCHEMANAME FROM V$SESSION;

CREATE OR REPLACE VIEW TP.STATS AS
SELECT STAT_NAME, VALUE FROM SYS.DBA_HIST_OSSTAT
WHERE STAT_NAME = 'NUM_CPUS' and ROWNUM = 1
UNION
SELECT STAT_NAME, VALUE FROM SYS.DBA_HIST_OSSTAT
WHERE STAT_NAME = 'NUM_CPU_CORES' and ROWNUM = 1
UNION
SELECT STAT_NAME, VALUE FROM SYS.DBA_HIST_OSSTAT
WHERE STAT_NAME = 'NUM_CPU_SOCKETS' and ROWNUM = 1
UNION
SELECT 'USERS' AS STAT_NAME, count(*) AS STAT_VALUE FROM SYS.DBA_USERS
UNION
SELECT 'SESSIONS' AS STAT_NAME, count(*) AS STAT_VALUE FROM SYS.V_$SESSION
UNION
SELECT 'SQL' AS STATE_NAME, count(*) AS STAT_VALUE FROM SYS.V_$SQL where first_load_time LIKE TO_CHAR(SYSDATE, 'yyyy-mm-dd%');


----------------------PERMISSIONS-----------------------------------------

GRANT SELECT ON TP.USERS TO TP;
GRANT SELECT ON TP.DATAFILES TO TP;
GRANT SELECT ON TP.TABLESPACES TO TP;
GRANT SELECT ON TP.STATS TO TP;
