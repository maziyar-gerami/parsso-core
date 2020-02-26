echo "--- Installing on `hostname`"
systemctl stop tomcat
rm -rf /opt/workspace/cas-overlay-template/build
./gradlew build
rm -rf /etc/cas/config
cp -i /opt/workspace/cas-overlay-template/build/libs/cas.war /opt/tomcat/latest/webapps/
cp -r /opt/workspace/cas-overlay-template/etc/cas/config/ /etc/cas/
chown root:tomcat /opt/tomcat/latest/webapps/cas.war
chown root:tomcat /etc/cas/config/
chmod g-w,o-rwx /opt/tomcat/latest/webapps/cas.war
chmod g-w,o-rwx /etc/cas/config/
systemctl start tomcat
echo "Installation complete."

