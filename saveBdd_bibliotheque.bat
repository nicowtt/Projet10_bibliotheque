@echo off
set jour=%date:~0,2%
set mois=%date:~3,2%
set annee=%date:~6,4%

SET PGPASSWORD=nicotine
SET DATABASENAME=bibliotheque
set DUMPOUT="C:\temp\pg_dump_bibliotheque_%jour%_%mois%_%annee%.dmp"
set PGDUMP="C:\Program Files\PostgreSQL\9.6\bin\pg_dump.exe"

%PGDUMP% -h localhost -U postgres -F c -f %DUMPOUT% %DATABASENAME%

echo Dump of %DATABASENAME%  base created on %DUMPOUT%

pause