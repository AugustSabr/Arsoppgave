#!/bin/bash

apt update
apt install ufw
ufw default deny incoming
ufw default allow outgoing

ufw allow OpenSSH
ufw allow in "WWW Full"

apt install apache2 -y
mkdir -p /var/www/Arsoppgave
chown -R l2 /var/www/Arsoppgave
chmod -R 755 /var/www/Arsoppgave
cat >> /etc/apache2/sites-available/Arsoppgave.conf << EOF
<VirtualHost *:80>
    ServerAdmin webmaster@localhost
    ServerName Arsoppgave
    ServerAlias www.Arsoppgave
    DocumentRoot /var/www/Arsoppgave
    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>
EOF
a2ensite Arsoppgave.conf
a2dissite 000-default.conf

apt-get install git git-core -y
git clone https://github.com/AugustSabr/JavaGame_AdminSite.git /var/www/Arsoppgave/

apt install php -y

apt -y install gnupg -y

sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main 15" > /etc/apt/sources.list.d/pgdg.list'
wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | apt-key add - 
apt update
apt-get -y install postgresql-15 -y
sed -i "s/#listen_addresses = 'localhost'/listen_addresses = '*'/" /etc/postgresql/15/main/postgresql.conf
ufw allow 5432

apt install -y phppgadmin apache2 -y
psql -U postgres -d myDataBase -a -f myInsertFile
sed -i 's/\(^.*conf\[.extra_login_security.\] =\) true/\1 false/' /etc/phppgadmin/config.inc.php
sed -i 's/Require local/Require all granted/' /etc/apache2/conf-enabled/phppgadmin.conf


sed -i 's/peer/md5/' /etc/postgresql/15/main/pg_hba.conf
sed -i 's/scram-sha-256/md5/' /etc/postgresql/15/main/pg_hba.conf
sed -i 's#127.0.0.1/32#0.0.0.0/0#' /etc/postgresql/15/main/pg_hba.conf
sudo service postgresql restart

psql postgres < /home/l2/DatabaseBackup/DBusers.sql postgres
psql game4 < /home/l2/DatabaseBackup/dump.sql postgres

systemctl restart apache2 -y
/etc/init.d/postgresql restart -y
ufw enable
systemctl restart networking.service -y