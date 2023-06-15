CREATE USER "Gameuser" WITH ENCRYPTED PASSWORD 'K3A*M5Y3!*iP^7';
ALTER ROLE "Gameuser" WITH LOGIN;
CREATE USER "Webuser" WITH ENCRYPTED PASSWORD 'B&A3!7E#wKd4y&';
ALTER ROLE "Webuser" WITH LOGIN;

CREATE DATABASE game4;
GRANT SELECT ON ALL TABLES IN SCHEMA "gameTables" TO "Gameuser";
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA "gameTables" TO "Webuser";
