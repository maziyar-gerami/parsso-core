for host in srv2 
do
scp /opt/workspace/parsso-core/build/libs/cas.war raad@${host}:cas.war
scp -r /opt/workspace/parsso-core/etc/cas/config raad@${host}:
scp -r /opt/workspace/parsso-core/etc/cas/services raad@${host}:

done

