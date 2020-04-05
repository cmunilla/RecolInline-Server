
Execute sql Main
===============
```bash
rm -rf jvm
jlink --module-path recolinline-server/RecolInline-Server/database/jpms-adaptive-hibernate/target/jpms-adaptive-hibernate-0.3.jar:recolinline-server/RecolInline-Server/database/jpms-adaptive-mysql/target/jpms-adaptive-mysql-0.3.jar:recolinline-server/RecolInline-Server/database/jpms-adaptive-jboss/target/jpms-adaptive-jboss-0.3.jar:recolinline-server/RecolInline-Server/database/sql/target/sql-0.3.jar:lyson/Lyson/target/lyson-0.1.jar --add-modules cmssi.recolinline.sql,jpms.adaptive.mysql,jpms.adaptive.hibernate,jpms.adaptive.jboss,cmssi.lyson --output ./jvm
keytool -genkeypair -alias cmssi.selfsigned -storepass cmssi.recolinline! -keypass cmssi.recolinline! -keystore keystore.jks -keyalg RSA -sigalg SHA256withRSA -validity 3650 -keysize 2048 -dname "CN=CM, OU=RecolInline, O=CMSSI, L=Chambéry, S=Savoie, C=FR"
keytool -importkeystore -srckeystore keystore.jks -destkeystore keystore.jks -deststoretype pkcs12
rm keystore.jks_old
mkdir ./jvm/cert
mv keystore.jks ./jvm/cert/keystore.jks
export cmssi.recolinline.keystore="`(pwd)`/jvm/cert/keystore.jks"
export cmssi.recolinline.keystore.type="pkcs12"
export cmssi.recolinline.keystore.storepass="cmssi.recolinline!"
export cmssi.recolinline.keystore.keypass="cmssi.recolinline!"
jvm/bin/java --add-opens java.base/java.lang=jpms.adaptive.hibernate cmssi.museum.Main
```