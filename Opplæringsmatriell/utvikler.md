# Utvikiler manual
Om du skal sette opp prosjektet selv har jeg gjort det enkelt for deg. Alt du trenger er å ha en debian pc med openssh-server. Tekst omringet av square brackets: [] er tekst du må fylle in selv
- Last ned github repositoriet, og pakk det ut
- Åpne cmd og bruk SCP til å Kopierere mappen "DatabaseBackup" i repositoriet til home directory til debian brukeren: 
~~~
scp -r [filbanen_Til_Mapen] [Debian_Brukeren]@[IPen_Til_Maskinen]:/home/[Debian_Brukeren]/
~~~
- Logg inn på maskinen (kan bruke ssh)
- Logg in som root user:
~~~
sudo su
~~~
- Gi scriptet tillatelse til å gjøre endringer
~~~
chmod 755 DatabaseBackup/setupServer.sh
sed -i -e 's/\r$//' DatabaseBackup/setupServer.sh
~~~
- Til slutt er det bare å skjøre skirptet:
~~~
./DatabaseBackup/setupServer.sh [Navnet_på_mappen_som_skal_ha_nettsidefilene] [Passordet_du_vil_at_postgres_brukeren_skal_ha]
~~~
Skriptet laster ned ufw, apache2, git, php, gnupg, postgresql-15, og phppgadmin. Skirptet lager også to nye psql brukere "Gameuser" med passord "K3A*M5Y3!*iP^7" og "Webuser" med passord "B&A3!7E#wKd4y&"(passordene kan du selv endre senere). Og til sist men ikke minst inporterer den all data som var på databasen ved forje backup til databasen "game4".