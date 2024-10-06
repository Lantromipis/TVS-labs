# Работа №1. Поиск с помощью SOAP-сервиса

## Дириктории с артефактами
1. client - содержит клиент для использования сервиса. Клиент представляет собой консольное приложение
2. j2ee - содержит j2ee приложение, развертываемое с помощью сервре wildfly
3. standalone - содержит standalone приложение развертываемое без помощи сервера приложений

## Подготовка к запуску 
1. Установить СУБД PostgreSQL
2. Проверить параметры подключения по умолчанию: адрес localhost, порт 5432, БД postgres, пользователь postgres, пароль postgres.
3. При отличии параметров от дефолтных, изменить их (для standalone внутри функции Main, для j2ee внутри конфигурации Wildfly standalone.xml -> DataSource).
4. Создать таблицу hero 
    ```
    CREATE TABLE hero(
      id BIGSERIAL PRIMARY KEY,
      name VARCHAR(128) UNIQUE,
      main_attribute VARCHAR(64),
      melee BOOLEAN,
      move_speed INT,
      damage INT,
      armor DOUBLE PRECISION
    );
    ```
5. Заполнить таблицу hero изначальными данными
   ```
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Pudge', 'STRENGTH', TRUE, 280, 73, 1.8);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Ogre Magi', 'STRENGTH', TRUE, 290, 70, 6.3);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Treant Protector', 'STRENGTH', TRUE, 280, 89, 1.5);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Huskar', 'STRENGTH', FALSE, 295, 46, 2.7);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Centaur Warrunner', 'STRENGTH', TRUE, 305, 64, 0.5);

    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Hoodwink', 'AGILITY', FALSE, 310, 49, 4);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Sniper', 'AGILITY', FALSE, 285, 43, 3.5);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Viper', 'AGILITY', FALSE, 275, 49, 1.5);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Spectre', 'AGILITY', TRUE, 290, 50, 4.2);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Juggernaut', 'AGILITY', TRUE, 300, 57, 5.7);

    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Jakiro', 'INTELLIGENCE', FALSE, 290, 57, 3.5);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Lion', 'INTELLIGENCE', FALSE, 290, 52, 2.5);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Oracle', 'INTELLIGENCE', FALSE, 295, 42, 3.5);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Grimstroke', 'INTELLIGENCE', FALSE, 290, 48, 3.0);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Silencer', 'INTELLIGENCE', FALSE, 290, 50, 3.7);

    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Phoenix', 'UNIVERSAL', FALSE, 280, 49, 1.0);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Abaddon', 'UNIVERSAL', TRUE, 325, 45, 3.8);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Clockwerk', 'UNIVERSAL', TRUE, 310, 49, 3.2);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Visage', 'UNIVERSAL', FALSE, 285, 47, 1.8);
    INSERT INTO hero (name, main_attribute, melee, move_speed, damage, armor) VALUES ('Invoker', 'UNIVERSAL', FALSE, 285, 43, 1.3);
   ```
6. Проверить, что в СУБД появились данные

## Запуск и проверка standalone сервиса
1. Установить JDK 17 и maven последней версии
2. Освободить порт 8080
3. `mvn clean install` внутри директории lab1-standalone
4. `java --add-opens java.base/java.lang=ALL-UNNAMED -jar lab1-standalone.jar` внутри директории lab1-standalone/target (--add-opens java.base/java.lang=ALL-UNNAMED необходим, так как все зависимости jax-ws устарели, а в JDK 17 изменилось API)
5. `mvn clean install` внутри директории lab1-client
6. `java --add-opens java.base/java.lang=ALL-UNNAMED -jar lab1-client.jar` внутри директории lab1-client/target 
7. Воспользоваться командой `help` и командой `listHeroes`
8. Для написания RSQL предикатов можно воспользоваться README из библиотеки https://github.com/jirutka/rsql-parser. Поддержаны логические операторы AND и OR, а также операторы сравнения `==`, `!=`, `=lt=`, `=le=`, `=gt=`, `=ge=`.

## Запуск и проверка j2ee сервиса
1. Установить JDK 17 и maven последней версии
2. Скачать и распоковать Wildfly 26.1.3.Final https://www.wildfly.org/downloads/
3. Запустить Wildfly
4. С помощью `<wildfly-dir>/bin/add-user.bat` создать пользователя для консоли управления Wildfly
5. Скачать драйвер PostgreSQL в виде jar https://jdbc.postgresql.org/download/ (Java 8)
6. Поместить .jar драйвера в `<wildfly-dir>/standalone/deployment`
7. Добавить datasource с именем `java:/PostgresDS`. Можно сделать через консоль управления, а можно изменив файл `<wildfly-dir>/standalone/configuration/standalone.xml` добавив в раздел <datasources> следующую запись:
```
<datasource jndi-name="java:/PostgresDS" pool-name="PostgresDS">
    <connection-url>jdbc:postgresql://localhost:5432/postgres</connection-url>
    <driver-class>org.postgresql.Driver</driver-class>
    <driver>postgresql-42.7.4.jar</driver>
    <security>
        <user-name>postgres</user-name>
        <password>postgres</password>
    </security>
    <validation>
        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
        <validate-on-match>true</validate-on-match>
        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
    </validation>
</datasource>
```
8. Освободить порт 8080
9. `mvn clean install` внутри директории lab1-j2ee
10. Задеплоить .war из /target
11. `mvn clean install` внутри директории lab1-client
12. `java --add-opens java.base/java.lang=ALL-UNNAMED -jar lab1-client.jar` внутри дирикетории lab1-client/target
14. Воспользоваться командой `help` и командой `listHeroes`
15. Для написания RSQL предикатов можно воспользоваться README из библиотеки https://github.com/jirutka/rsql-parser. Поддержаны логические операторы AND и OR, а также операторы сравнения `==`, `!=`, `=lt=`, `=le=`, `=gt=`, `=ge=`.


## Примеры RSQL предикатов для тестирования:
1. `melee==true` -- получить всех героев ближнего боя
2. `melee==true;mainAttribute==UNIVERSAL` -- получить всех универсальных героев ближнего боя
3. `moveSpeed=ge=295;(mainAttribute==AGILITY,mainAttribute==INTELLIGENCE)` -- получить всех героев со скоростью передвижения более или равной 295, главный атрибут которых ловкость или интеллект
4. `(damage=ge=50;mainAttribute==INTELLIGENCE),(mainAttribute==UNIVERSAL;armor=le=3.5;armor=ge=1.5),(mainAttribute==STRENGTH;melee==true;damage=gt=70)` -- получить всех героев, чей основной атрибут интеллект и урон больше или равен 50, а также героев универсальных героев чья броня (1.5,3.5), а также героев ближнего боя, чей основной атрибут сила и урон более 70.