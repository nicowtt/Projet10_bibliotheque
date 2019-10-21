@echo off
set jour=%date:~0,2%
set mois=%date:~3,2%
set annee=%date:~6,4%

SET PGPASSWORD=nicotine
SET DATABASENAME=bibliotheque
set DUMPIN=C:\temp\pg_dump_bibliotheque_%jour%_%mois%_%annee%.dmp
set PSQL="C:\Program Files\PostgreSQL\9.6\bin\psql.exe"
set PGRESTORE="C:\Program Files\PostgreSQL\9.6\bin\pg_restore.exe"


%PSQL% -U postgres -c "drop database %DATABASENAME%"
echo Database %DATABASENAME% deleted

%PSQL% -U postgres -c "CREATE DATABASE %DATABASENAME%"
echo Database %DATABASENAME% created

%PGRESTORE% -h localhost -d bibliotheque -F c -U postgres %DUMPIN%

echo  *******  restoration ok with %DUMPIN% *******

pause