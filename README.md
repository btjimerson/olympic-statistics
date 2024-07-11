# Distributed SQL Modeling with Olympic Statistics

## Introduction
This application demonstrates the impact of selecting appropriate partition keys in a distributed SQL system such as YugabyteDB.

There are two identical tables in the database; the only difference is the partition key for the table:

```bash
olympic=> \d athlete_before
                  Table "public.athlete_before"
 Column |         Type          | Collation | Nullable | Default
--------+-----------------------+-----------+----------+---------
 id     | integer               |           | not null |
 name   | text                  |           |          |
 sex    | character(1)          |           |          |
 age    | character varying(25) |           |          |
 height | character varying(25) |           |          |
 weight | character varying(25) |           |          |
 team   | text                  |           |          |
 noc    | character(3)          |           |          |
 games  | character varying(15) |           |          |
 year   | integer               |           | not null |
 season | character(6)          |           |          |
 city   | text                  |           |          |
 sport  | text                  |           |          |
 event  | text                  |           | not null |
 medal  | character varying(8)  |           |          |
Indexes:
    "athlete_before_pkey" PRIMARY KEY, lsm (id HASH, event ASC, year ASC)
    "name_before" lsm (name HASH, year ASC)

olympic=> \d athlete_after
                  Table "public.athlete_after"
 Column |         Type          | Collation | Nullable | Default
--------+-----------------------+-----------+----------+---------
 id     | integer               |           | not null |
 name   | text                  |           |          |
 sex    | character(1)          |           |          |
 age    | character varying(25) |           |          |
 height | character varying(25) |           |          |
 weight | character varying(25) |           |          |
 team   | text                  |           |          |
 noc    | character(3)          |           |          |
 games  | character varying(15) |           |          |
 year   | integer               |           | not null |
 season | character(6)          |           |          |
 city   | text                  |           |          |
 sport  | text                  |           |          |
 event  | text                  |           | not null |
 medal  | character varying(8)  |           |          |
Indexes:
    "athlete_after_pkey" PRIMARY KEY, lsm (event HASH, year DESC, id ASC)
```

## Running

First, make sure you have the following installed:
 * Java JDK 17+
 * Maven

To run this demonstration, you need to set a few environment variables for the Yugabyte connection:

```bash
export YB_URL=jdbc:yugabytedb://localhost:5433/yugabyte #JDBC URL
export YB_USER=yugabyte #Username for authentication
export YB_PASSWORD=password #Password for authentication
```

Then run it as a normal Spring Boot application:

```bash
mvn spring-boot:run
```

Alternatively, you build it and run the executable jar:

```bash
mvn clean package
cd target/
java -jar olympic-statistics-0.0.1-SNAPSHOT.jar
```

You can open a browser and navigate to `http://localhost:8080`. You will see two buttons, 'Show Medals - ID PK' and 'Show Medals - Event PK'. Select an event from the drop down, click the two buttons, and notice the difference in query times between the two partition keys.

![Screenshot](/images/main-page.png)
