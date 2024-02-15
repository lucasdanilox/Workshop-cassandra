# Workshop Spring Boot Cassandra DevSuperior

### Modelo conceitual

![Modelo de domínio ](https://i.postimg.cc/xdD4f5vv/DSProduct.png)

# Escopo de projeto
CRUD completo de Departamentos (incluindo listagem de departamentos para que o usuário selecione um departamento)
Busca de produtos por nome do departamento
Busca de produtos por texto na descrição
Buscar todos detalhes de um produto inclusive suas propriedades.


### Container Docker Cassandra para desenvolvimento

```
docker run -d -p 9042:9042 --name cassandra1 cassandra:3.11.10
```

```
docker exec -it cassandra1 bash
```
Arquivo properties
```
spring.data.cassandra.contact-points=localhost
spring.data.cassandra.keyspace-name=productsdb
spring.data.cassandra.port=9042

spring.data.cassandra.local-datacenter=datacenter1
```

cqlsh

```java
describe keyspaces

CREATE KEYSPACE testdb WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};

use testdb;

describe tables

CREATE TABLE post(id uuid, moment timestamp, body text, author varchar, PRIMARY KEY (id));

INSERT INTO post (id, moment, body, author) VALUES (uuid(), '2021-02-26T10:00:00Z', 'Bom dia!', 'Bob');
INSERT INTO post (id, moment, body, author) VALUES (uuid(), '2021-02-27T10:00:00Z', 'Partiu viagem', 'Maria');
INSERT INTO post (id, moment, body, author) VALUES (uuid(), '2021-02-27T10:00:00Z', 'Que dia bonito!', 'Maria');

SELECT * FROM post;

SELECT * FROM post WHERE author = 'Maria' ALLOW FILTERING;

CREATE CUSTOM INDEX body_idx ON post (body) USING 'org.apache.cassandra.index.sasi.SASIIndex' WITH OPTIONS = {'mode': 'CONTAINS', 'analyzer_class': 'org.apache.cassandra.index.sasi.analyzer.NonTokenizingAnalyzer','case_sensitive': 'false'};

SELECT * FROM post WHERE body LIKE '%MORNING%';
```
