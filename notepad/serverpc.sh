apt update

name=$1

echo "Setup for user $name"
adduser $name
usermod -aG sudo $name

apt install openssh-server -y
nano '/etc/network/interfaces'
systemctl restart networking

apt install apache2 -y
systemctl start apache2
systemctl enable apache2

mkdir -p /var/www/Arsoppgave
chown -R $USER:$USER /var/www/Arsoppgave
chmod -R 755 /var/www/Arsoppgave
nano /etc/apache2/sites-available/Arsoppgave.conf
a2ensite Arsoppgave.conf
a2dissite 000-default.conf
apache2ctl configtest
systemctl restart apache2

ufw allow OpenSSH
ufw allow in "WWW Full"

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
nano /etc/phppgadmin/config.inc.php
nano /etc/apache2/conf-enabled/phppgadmin.conf
nano /etc/phppgadmin/config.inc.php