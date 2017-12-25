

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
GRANT SELECT ON SYS.v_$sesstat TO TP;
GRANT SELECT ON SYS.v_$bgprocess TO TP;
GRANT SELECT ON SYS.v_$process TO TP;
GRANT SELECT ON SYS.v_$instance TO TP;
GRANT SELECT ON SYS.v_$statname TO TP;

-- ------------------CREATE VIEWS----------------------------------------

CREATE OR REPLACE VIEW TP.USERS AS SELECT USER_ID, USERNAME, CREATED, ACCOUNT_STATUS FROM SYS.DBA_USERS;

CREATE OR REPLACE VIEW TP.TABLESPACES AS SELECT TABLESPACE_NAME, STATUS FROM SYS.DBA_TABLESPACES;

CREATE OR REPLACE VIEW TP.DATAFILES AS SELECT FILE_ID, FILE_NAME, BYTES, STATUS, TABLESPACE_NAME FROM SYS.DBA_DATA_FILES;

CREATE OR REPLACE VIEW TP.SESSIONS AS SELECT SID,USERNAME,MACHINE,STATUS,PROGRAM,TYPE,SCHEMANAME FROM V$SESSION;

CREATE OR REPLACE VIEW TP.MEMORY AS
SELECT ssn.sid AS SID, ROUND((se1.value/1024)/1024, 2) AS CURRENTSIZE, ROUND((se2.value/1024)/1024,2) AS MAXIMUMSIZE
FROM SYS.v_$sesstat se1, SYS.v_$sesstat se2, SYS.v_$session ssn, SYS.v_$bgprocess bgp, SYS.v_$process prc,SYS.v_$instance ins, SYS.v_$statname stat1, SYS.v_$statname stat2
WHERE se1.statistic# = stat1.statistic# and stat1.name = 'session pga memory'
AND se2.statistic# = stat2.statistic# and stat2.name = 'session pga memory max'
AND se1.sid = ssn.sid
AND se2.sid = ssn.sid
AND ssn.paddr = bgp.paddr (+)
AND ssn.paddr = prc.addr (+);

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
SELECT 'SQL' AS STAT_NAME, count(*) AS STAT_VALUE FROM SYS.V_$SQL where first_load_time LIKE TO_CHAR(SYSDATE, 'yyyy-mm-dd%')
UNION
SELECT 'MEMORY' AS STAT_NAME, sum(to_char((se1.value/1024)/1024, '999G999G990D00')) AS STAT_VALUE
FROM SYS.v_$sesstat se1, SYS.v_$sesstat se2, SYS.v_$session ssn, SYS.v_$bgprocess bgp, SYS.v_$process prc,
SYS.v_$instance ins, SYS.v_$statname stat1, SYS.v_$statname stat2
WHERE se1.statistic# = stat1.statistic# and stat1.name = 'session pga memory'
AND se2.statistic# = stat2.statistic# and stat2.name = 'session pga memory max'
AND se1.sid = ssn.sid
AND se2.sid = ssn.sid
AND ssn.paddr = bgp.paddr (+)
AND ssn.paddr = prc.addr (+);


----------------------PERMISSIONS-----------------------------------------

GRANT SELECT ON TP.USERS TO TP;
GRANT SELECT ON TP.DATAFILES TO TP;
GRANT SELECT ON TP.TABLESPACES TO TP;
GRANT SELECT ON TP.STATS TO TP;
