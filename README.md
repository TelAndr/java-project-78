### Hexlet tests and linter status:
[![Actions Status](https://github.com/TelAndr/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/TelAndr/java-project-78/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=TelAndr_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=TelAndr_java-project-78)
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=TelAndr_java-project-78)](https://sonarcloud.io/summary/new_code?id=TelAndr_java-project-78)
[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-light.svg)](https://sonarcloud.io/summary/new_code?id=TelAndr_java-project-78)

```md
# Описание проекта

В этом проекте производится проверка входных данных на соответствие правилам, 
которые задаются посредством специальных методов классов конкретных схем данных,
таких как `StringSchema`, `NumberSchema`, `MapSchema`
для проверки строковых объектов, числовых объектов и объектов типа `map`.

Для валидации данных сначала создаётся объект валидатора:

```java
var v = new Validator();


Далее создаётся конкретная схема через вызов конкретного метода валидатора:

- `string()`
- `number()`
- `map()`

Для конфигурации схемы применяются соответствующие методы конкретных схем данных.
После настройки конфигурации конкретной схемы происходит проверка данных
посредством вызова на объекте схемы метода `isValid`.

В аргумент метода `isValid` передаются конкретные данные. 
Результатом работы будет логическое значение `true`,
если данные соответствуют всем заданным в схеме правилам,
или `false`, если не соответствуют.
```