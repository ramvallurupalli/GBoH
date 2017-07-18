# Big Oh

## Flyway

Use this script to populate your local Docker **bigoh** database with the schema and certain configurations.

You will need to run it with `big-oh/web` as the working directory.

Make sure that `big-oh/pom.xml` has the correct `<big-oh.version>` for your project.  Choose a version from https://github.com/iFactor/big-oh/releases

```
mvn clean install flyway:migrate -Ddb.environment=local -Ddb.url=jdbc:oracle:thin:@localhost:1521:xe -Dflyway.user=bigoh -Dflyway.password=Password0
```

Naturally, you can modify this script to use it to seed other non-local databases.