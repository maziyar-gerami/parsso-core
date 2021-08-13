
echo "--- Installing on `hostname`"

systemctl stop tomcat
rm -rf /opt/workspace/parsso-core/build
#rm -rf /etc/cas/config
#rm -rf /etc/cas/services

./gradlew build

cd /opt/tomcat/latest/
rm -rf webapps/cas work/Catalina/localhost/cas
cd /

cp /opt/workspace/parsso-core/build/libs/cas.war /opt/tomcat/latest/webapps/
#cp -r /opt/workspace/parsso-core/etc/cas/config /etc/cas/
#cp -r /opt/workspace/parsso-core/etc/cas/services /etc/cas/

chown root:tomcat /opt/tomcat/latest/webapps/cas.war
chown root:tomcat /etc/cas/config/
chmod g-w,o-rwx /opt/tomcat/latest/webapps/cas.war
chmod g-w,o-rwx /etc/cas/config/

systemctl start tomcat
echo "Installation complete."
tail -f  /opt/tomcat/latest/logs/catalina.out

