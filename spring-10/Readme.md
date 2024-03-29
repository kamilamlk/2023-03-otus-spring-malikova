# Задание 10 #
Переписать приложение с использованием AJAX и REST-контроллеров
### Цель:
Использовать Spring MVC для разработки современных AJAX/SPA приложений c помощью Spring MVC
### Результат: 
Современное приложение на стеке Spring

### Описание/Пошаговая инструкция выполнения домашнего задания:
Задание может выполняться на основе предыдущего, а может быть выполнено самостоятельно.
### Требования:
1. Переписать приложение с классических View на AJAX архитектуру и REST-контроллеры; 
2. Минимум: получение одной сущности и отображение её на странице с помощью XmlHttpRequest, fetch api или jQuery; 
3. Опционально максимум: полноценное SPA приложение на React/Vue/Angular, только REST-контроллеры; 
4. Протестировать все эндпойнты REST-контроллеров с моками зависимостей; 
5. Без фанатизма
   В случае разработки SPA - рекомендуется вынести репозиторий с front-end. Используйте proxy при разработке (настройки CORS не должно быть).

## Сборка и запуск приложения ##
1. Сборка приложения
   ```
   mvn package
   ```
2. Запуск приложения
   ```
   java -jar target/spring-10-0.0.1-SNAPSHOT-exec.jar
   ```