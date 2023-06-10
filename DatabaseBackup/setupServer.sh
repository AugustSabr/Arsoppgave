apt update

# name=$1

# echo "Setup for user $name"
# adduser $name
# usermod -aG sudo $name

sudo apt install ufw
sudo ufw default deny incoming
sudo ufw default allow outgoing

apt install openssh-server -y
ufw allow OpenSSH
ufw allow in "WWW Full"

apt install apache2 -y
systemctl start apache2
systemctl enable apache2

mkdir -p /var/www/Arsoppgave
chown -R $USER:$USER /var/www/Arsoppgave
chmod -R 755 /var/www/Arsoppgave
# nano /etc/apache2/sites-available/Arsoppgave.conf
a2ensite Arsoppgave.conf
a2dissite 000-default.conf
apache2ctl configtest
systemctl restart apache2



apt-get install git git-core -y
clone https://github.com/AugustSabr/JavaGame_AdminSite.git
git pull

apt install php -y
tail -f /var/log/apache2/error.log

apt -y install gnupg -y

sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main 15" > /etc/apt/sources.list.d/pgdg.list'
wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | apt-key add - 
apt update
apt-get -y install postgresql-15 -y
systemctl status postgresql
/etc/init.d/postgresql restart

apt install -y phppgadmin apache2 -y
# nano /etc/phppgadmin/config.inc.php
# nano /etc/apache2/conf-enabled/phppgadmin.conf
# nano /etc/phppgadmin/config.inc.php

systemctl restart networking

echo 'Dette skriptet laster ned det du trenger for å drifte Årsoppgaven, men du må fortsatt endre et par filer selv:
Filbane: /etc/network/interfaces (om du vil ha statisk ip):
# the loopback network interfaces
auto lo
iface lo inet loopback
# The primary network interface
auto enp2s0
iface enp2s0 inet static
	address "din statiske ip-adresse"
	netmask "velg en netmask som passer med ip-adressen"
	gateway "ip-en til rutern/switch-en"


Filbane /etc/apache2/sites-available/Arsoppgave.conf (jeg bruker en virituel host for å kunne hoste flere netsidder samtidig):
<VirtualHost *:80>
    ServerAdmin webmaster@localhost
    ServerName your_domain
    ServerAlias www.your_domain
    DocumentRoot /var/www/your_domain
    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>'

# /etc/phppgadmin/config.inc.php (må endre på premissions for å ):
# $conf['extra_login_security'] = false;
# $conf['owned_only'] = true;


# /etc/apache2/conf-enabled/phppgadmin.conf:
# endre Require local til Require all granted"