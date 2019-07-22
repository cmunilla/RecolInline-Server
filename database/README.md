
Execute sql Main
===============
```bash
rm -rf jvm
jlink --module-path jpms-adaptive-hibernate/target/jpms-adaptive-hibernate-0.3.jar:jpms-adaptive-mysql/target/jpms-adaptive-mysql-0.3.jar:jpms-adaptive-jboss/target/jpms-adaptive-jboss-0.3.jar:sql/target/sql-0.3.jar --add-modules cmssi.recolinline.sql,jpms.adaptive.mysql,jpms.adaptive.hibernate,jpms.adaptive.jboss --output ./jvm
java --add-opens java.base/java.lang=jpms.adaptive.hibernate cmssi.museum.Main
```