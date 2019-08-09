# About

This project sets up a pre-packaged example template of a SQL database backed REST API application. It is intended
to be used for bootstrapping the back-end of a data-driven application.


# Prerequisites

You must have the following infrastructure components already installed and working on your machine:

- Java 1.8+
- Docker 18.x+
- Maven 3.x+

## Database setup


### PostgreSQL

This project uses Postgres 11 as the database server, and all database code makes this assumption. 
It would be relatively easy to adjust the template to use a different database, but that's outside
the scope of this document. 

To start Postgres, we use a few simple commands:

```
$ docker pull postgres:11
$ DATADIR=/home/sroy/pgdata
$ DBPASSWORD=testingpg123
$ mkdir -p ${DATADIR}
$ docker run --name dbserver --mount type=bind,source="${DATADIR}",target="/pgdata" -p 5432:5432/tcp -e PGDATA="/pgdata" -e POSTGRES_PASSWORD=${DBPASSWORD} -d postgres
```

To validate that the application can talk to the database, you can run the dbtester app.

```
$ mvn -DskipTests package -Dbin
$ bash target/bin/dbtester
```

### Create the database

Run psql, as shown below. Then:

```
postgres=# create database carstore;
```

### Schema Management

The schema is managed using the open source [liquibase](https://www.liquibase.org/documentation/maven/index.html) tool, via Maven plugin interface.

To install / update the schema, just run the following command:

```
$ mvn liquibase:update
```

Construct the schema using [liquibase changesets](https://www.liquibase.org/documentation/sql_format.html) by editing the __db.changelog.sql__ file.

## Optional Tooling

### Using psql

```
docker exec -i -t dbserver /usr/bin/psql -h localhost -U postgres
```

Try listing the databases.

```
postgres=# \l
```

You should see the new database in the list of databases.

Explore other commands:

```
postgres=# \?
```

### Install pgadmin3 as Postgres admin UI

```
sudo apt-get install pgadmin3
```

### Install Squirrel SQL

Download Squirrel SQL from [here](https://sourceforge.net/projects/squirrel-sql/files/1-stable/3.9.1/squirrel-sql-3.9.1-standard.jar/download).

For hidpi monitors, make the following change to startup script to set better font sizes:

```
# Launch SQuirreL application
"$JAVACMD" -Dswing.plaf.metal.controlFont=Tahoma-24 -Dswing.plaf.metal.userFont=Tahoma-24 -cp "$CP" -splash:"$SQUIRREL_SQL_HOME/icons/splash.jpg" net.sourceforge.squirrel_sql.client.Main --log-config-file "$UNIX_STYLE_HOME"/log4j.properties --squirrel-home "$UNIX_STYLE_HOME" $NATIVE_LAF_PROP $SCRIPT_ARGS
```

Also, set a larger font size under New Session Preferences -> SQL

# Build

For local test runs:
```
$ mvn -DskipTests package -Dbin
```

For fatjar deployment:
```
$ mvn -DskipTests package -Dfatjar
```

# Development

This project provides samples of various functionality useful for quickly building a RDBMS backed REST API.

## Database Manipulation

To manipulate the database from the application, this project uses the [MyBatis 3](http://www.mybatis.org/mybatis-3/) framework.

Without repeating all of the MyBatis documentation, in general you will need 3 things per database entity:

- An entity pojo (see Account.java)
- An entity mapper interface (see AccountMapper.java)
- A sql mapper xml definition (see AccountMapper.xml)

For an example of how to actually interact with the database, see MyBatisTester.java
