INSERT INTO TP.USERS (user_id, username, created, status)
SELECT USER_ID, USERNAME, CREATED, ACCOUNT_STATUS
FROM SYS.DBA_USERS;

INSERT INTO TP.TABLESPACES (name, status)
SELECT TABLESPACE_NAME, STATUS
FROM SYS.DBA_TABLESPACES;

INSERT INTO TP.DATAFILES (datafile_id, name, datafile_size, status, tablespace_name)
SELECT FILE_ID, FILE_NAME, BYTES, STATUS, TABLESPACE_NAME
FROM SYS.DBA_DATA_FILES;

INSERT INTO TP.SESSIONS (session_id, username, machine, status, program, type, schema)
SELECT SID,USERNAME,MACHINE,STATUS,PROGRAM,TYPE,SCHEMANAME
FROM V$SESSION;

INSERT INTO TP.MEMORY (sid, current_size, maximum_size)
SELECT ssn.sid, ROUND((se1.value/1024)/1024, 2), ROUND((se2.value/1024)/1024,2)
FROM SYS.v_$sesstat se1, SYS.v_$sesstat se2, SYS.v_$session ssn, SYS.v_$bgprocess bgp, SYS.v_$process prc,SYS.v_$instance ins, SYS.v_$statname stat1, SYS.v_$statname stat2
WHERE se1.statistic# = stat1.statistic# and stat1.name = 'session pga memory'
AND se2.statistic# = stat2.statistic# and stat2.name = 'session pga memory max'
AND se1.sid = ssn.sid
AND se2.sid = ssn.sid
AND ssn.paddr = bgp.paddr (+)
AND ssn.paddr = prc.addr (+);

INSERT INTO TP.STATS (stat_name, stat_value)
SELECT DISTINCT STAT_NAME, VALUE FROM SYS.DBA_HIST_OSSTAT
WHERE STAT_NAME = 'NUM_CPUS'
UNION
SELECT DISTINCT STAT_NAME, VALUE FROM SYS.DBA_HIST_OSSTAT
WHERE STAT_NAME = 'NUM_CPU_CORES'
UNION
SELECT DISTINCT STAT_NAME, VALUE FROM SYS.DBA_HIST_OSSTAT
WHERE STAT_NAME = 'NUM_CPU_SOCKETS';

INSERT INTO TP.STATS (stat_name, stat_value)
SELECT 'USERS', count(*) FROM SYS.DBA_USERS
UNION
SELECT 'SESSIONS' , count(*) FROM SYS.V_$SESSION
UNION
SELECT 'SQL', count(*) FROM SYS.V_$SQL where first_load_time LIKE TO_CHAR(SYSDATE, 'yyyy-mm-dd%')
UNION
SELECT 'MEMORY', sum(to_char((se1.value/1024)/1024, '999G999G990D00'))
FROM SYS.v_$sesstat se1, SYS.v_$sesstat se2, SYS.v_$session ssn, SYS.v_$bgprocess bgp, SYS.v_$process prc,
SYS.v_$instance ins, SYS.v_$statname stat1, SYS.v_$statname stat2
WHERE se1.statistic# = stat1.statistic# and stat1.name = 'session pga memory'
AND se2.statistic# = stat2.statistic# and stat2.name = 'session pga memory max'
AND se1.sid = ssn.sid
AND se2.sid = ssn.sid
AND ssn.paddr = bgp.paddr (+)
AND ssn.paddr = prc.addr (+);
