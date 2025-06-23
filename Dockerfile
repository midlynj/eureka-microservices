FROM container-registry.oracle.com/database/free
ENV ORACLE_SID=FREE
ENV ORACLE_PDB=FREEPDB1
ENV ORACLE_EDITION=developer

COPY data.sql /docker-entrypoint-initdb.d

EXPOSE 1521