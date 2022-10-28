# spring-boot-security-form-login

É necessário estar instalado o Java e variáveis de ambiente para funcionar, no exemplo estamos usamos Java 11

O app está configurado com spring-boot + spring-security + thymeleaf.
Esse exemplo está realizando login com pagina customizada e banco de dados, é um bom aplicativo para quem está iniciando com spring-boot + spring-security


> Spring Boot 2.7.5

> Java 11


## Dependency
– If you want to use PostgreSQL:
```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```

– or MySQL:
```xml
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
</dependency>
```
## Configure Spring Datasource, JPA, App properties
Open `src/main/resources/application.properties`
- For PostgreSQL:

spring.datasource.url= jdbc:postgresql://localhost:5432/testdb
spring.datasource.username= postgres
spring.datasource.password= 123

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto= update

# Create Database
```
CREATE DATABASE testdb;
```

## Run Spring Boot application
- mvn spring-boot:run

- Windows 
./mvnw spring-boot:run

## Run following SQL insert statements
```
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users (email, password, username) VALUES ('mod@gmail.com', '$2a$10$tEISeP5Vj8enWb1j0lqupeigfd06hOl6PUA42GAeXBgBn4ihHp3C6', 'mod');
INSERT INTO users(email, password, username) VALUES ('user@gmail.com', '$2a$10$URVIh0qw7xVIXl.jXnmD6OlRVTjVKoHUzTFocO3HxIvu.lsenvbb6', 'user');
INSERT INTO users (email, password, username) VALUES('admin@gmail.com', '$2a$10$hG.NiOiU0w.LmuAjivJKfuFUtETFh1yZoyTi3AcCS44XcCHOkqWpG', 'admin');

INSERT INTO user_roles (user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES(2, 1);
INSERT INTO user_roles (user_id, role_id) VALUES(1, 2);
INSERT INTO user_roles (user_id, role_id) VALUES(3, 3);

```


## Examples login
user: user
password: 12345678

user: admin
password: 12345678

user: mod
password: 12345678