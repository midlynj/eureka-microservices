This is a mircoservice Springboot that uses Eureka as a discovery service.
Spring cloud gateway as centralized route(deprecated)

Product-Service uses Coupon-Service as a feign client to make it easier for fault tolerance
Redis for caching

Now down to the nitty gritty to start the application
Ensure podman or Docker is running

Execute:
podman-compose up (build images and run container) or pod-compose up --build(source code changes)

[//]: # (# Services will fail due to user isn't created yet by the DBA)
[//]: # (# Once database is running run these commands)

podman exec -it [container_name] /bin/bash

[//]: # (# Integrated terminal for the database)

sqlplus sys/oracle_password as sysdba

[//]: # (# Logging in as DBA)

ALTER SESSION SET CONTAINER = FREEPDB1;

[//]: # (# Connect to your PDB)

CREATE USER [username] IDENTIFIED BY [password];

[//]: # (# creates user)

GRANT CONNECT, RESOURCE, DBA, CREATE PROCEDURE,  CREATE TABLE, CREATE VIEW,CREATE SESSION,  DROP ANY TABLE,  UNLIMITED TABLESPACE TO [username];

[//]: # (# Grants for users)

SELECT username FROM all_users;

[//]: # (# Verify user exists)


localhost:8761 for eureka server to see services

