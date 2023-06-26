# Задание 8 #
Использовать MongoDB и spring-data для хранения информации о книгах
### Цель:
После выполнения ДЗ вы сможете использовать Spring Data MongoDB и саму MongoDB для разработки приложений с хранением данных в нереляционной БД. 
### Результат: 
Приложение с использованием MongoDB

### Описание/Пошаговая инструкция выполнения домашнего задания:
Задание может выполняться на основе предыдущего, а может быть выполнено самостоятельно.
### Требования:
1. Использовать Spring Data MongoDB репозитории, а если не хватает функциональности, то и *Operations 
2. Тесты можно реализовать с помощью Flapdoodle Embedded MongoDB 
3. Hibernate, равно, как и JPA, и spring-boot-starter-data-jpa не должно остаться в зависимостях, если ДЗ выполняется на основе предыдущего. 
4. Как хранить книги, авторов, жанры и комментарии решать Вам. Но перенесённая с реляционной базы структура не всегда будет подходить для MongoDB.

## Сборка и запуск приложения ##
1. Сборка приложения
   ```
   mvn package
   ```
2. Запуск приложения
   ```
   java -jar target/spring-08-0.0.1-SNAPSHOT-exec.jar
   ```
3. Отобразить все книги
   ```
   books
   ```
4. Отобразить все жанры
   ```
   genres
   ```
5. Отобразить всех авторов
   ```
   authors
   ```
6. Отобразить книгу, id - идентификатор книги
   ```
   book <id>
   ```
7. Добавить книгу, TITLE - название книги, YEAR - год публикации, AUTHOR - идентификатор автора в базе, GENRE - идентификатор жанра в базе
   ```
   book-add -t TITLE -y YEAR -a AUTHOR -g GENRE
   ```
   ```
   book-add --title 1984 --year 1948 --author 2 --genre 2
   ```
8. Обновить данные о книге, TITLE - название книги, YEAR - год публикации, AUTHOR - идентификатор автора в базе, GENRE - идентификатор жанра в базе
   ```
   book-update id -t TITLE -y YEAR -a AUTHOR -g GENRE
   ```
   ```
   book-update 2 --title 1984 --year 1949 --author 2 --genre 2
   ```
9. Добавить комментарий
   ```
   comment-add <bookId> <comment text>
   ```
   ```
   comment-add 1 "Facinating"
   ```
10. Обновить комментарий
   ```
   comment-update <commentId> <comment new text>
   ```
   ```
   comment-update 1 "Facinating book"
   ```
11. Посмотреть комментарии к книге
   ```
   comments <bookId>
   ```
   ```
   comments 1
   ```
12. Удалить комментарий
   ```
   comment-delete <commentId>
   ```
   ```
   comment-delete 1
   ```
13. Обновить информацию об авторе автора
   ```
   author-update <authorID> <new name>
   ```
   ```
   author-update 1 "Stephen King 2"
   ```
14. Добавить автора
   ```
   author-add <name>
   ```
   ```
   author-add "Stephen King 2"
   ```
15. Справка
   ```
   help
   ```
16. Завершение работы
   ```
   exit
   ```
