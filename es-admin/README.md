# ES Admin

## Flyway

Use this script to populate your local Docker **es_admin** database with the schema and certain configurations. We are no longer using the old `seed.sql` files.

You will need to run it with `es-admin/web-impl` as the working directory.

Make sure that `es-admin/pom.xml` has the correct `<es-admin.version>` for your project.  Choose a version from https://github.com/iFactor/es-admin/releases

```
mvn clean install flyway:migrate -Ddb.environment=local -Ddb.url=jdbc:oracle:thin:@localhost:1521:xe -Dflyway.user=es_admin -Dflyway.password=Password0
```

Naturally, you can modify this script to use it to seed other non-local databases. When you do see non-local databases, be sure to set `-Ddb.environment=(dev|test|prod)`