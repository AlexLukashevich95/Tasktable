FROM postgres:latest

COPY database/ddl/create_tasktable.sql /docker-entrypoint-initdb.d/create_tasktable.sql
COPY database/dml/insert_tasks.sql /docker-entrypoint-initdb.d/insert_tasks.sql