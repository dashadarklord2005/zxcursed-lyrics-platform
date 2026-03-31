# Lyric Annotations 💀🖤 - Gothic Fan Platform for ZXC CURSED

Тёмная платформа для истинных фанатов с атмосферой мёртвых внутри

## 🚀 Быстрый старт 💀

### Предварительные требования
- Java 17+ 💀
- Maven 3.8+ ⚙️
- PostgreSQL 15+ 🐘 (H2 для разработки)
- Docker & Docker Compose 🐳

## 🕯️ Установка и запуск

### Способ 1: Через IDE
```bash
Откройте проект в IntelliJ IDEA и запустите файл:
src/main/java/com/zxc/lyricannotations/LyricAnnotationsApplication.java
```
### Способ 2: Быстрый запуск с Docker ⚰️
```bash
# Клонируйте проект и запустите одной командой
docker-compose up --build

# Приложение будет доступно по адресу:
# http://localhost:8080
```
Способ 3: Локальная разработка 🌑
```bash
# 1. Установите PostgreSQL и создайте базу данных
createdb lyricannotations

# 2. Настройте подключение в application.properties
# spring.datasource.url=jdbc:postgresql://localhost:5432/lyricannotations
# spring.datasource.username=postgres
# spring.datasource.password=yourpassword

# 3. Соберите и запустите приложение
mvn clean package
java -jar target/lyric-annotations-0.0.1-SNAPSHOT.jar 🖤
```
Способ 4: Режим разработки с H2 👻
```bash
# Используйте встроенную H2 базу данных
mvn spring-boot:run

# H2 Console будет доступна по адресу:
# http://localhost:8080/h2-console
# JDBC URL: jdbc:h2:mem:testdb
# Username: sa
# Password: (оставьте пустым) 🔑
```
📂 Структура проекта 🕸️
```bash
lyric-annotations/
├── src/
│   ├── main/
│   │   ├── java/com/zxc/lyricannotations/
│   │   │   ├── LyricAnnotationsApplication.java    # Точка входа Spring Boot ⚰️
│   │   │   ├── config/                            # Конфигурационные классы 🧙
│   │   │   │   └── WebConfig.java                 # Конфигурация статических ресурсов
│   │   │   ├── controller/                        # Контроллеры (MVC) 🎭
│   │   │   │   ├── HomeController.java           # Главная страница 🏚️
│   │   │   │   ├── AlbumController.java          # Управление альбомами ⚰️
│   │   │   │   ├── SongController.java           # Управление песнями 🎶
│   │   │   │   ├── AuthController.java           # Регистрация/авторизация 🔐
│   │   │   │   ├── ProfileController.java        # Профиль пользователя 👤
│   │   │   │   ├── SearchController.java         # Поиск 🔮
│   │   │   │   ├── UserProfileController.java    # Публичные профили 💀
│   │   │   │   └── GlobalExceptionHandler.java   # Обработка ошибок ☠️
│   │   │   ├── entity/                            # Сущности БД (JPA) 📜
│   │   │   │   ├── User.java                     # Пользователи 🧛
│   │   │   │   ├── Album.java                    # Альбомы ⚰️
│   │   │   │   ├── Song.java                     # Песни 🎶
│   │   │   │   ├── AlbumComment.java             # Комментарии к альбомам 💬
│   │   │   │   ├── SongComment.java              # Комментарии к песням 📝
│   │   │   │   ├── Annotation.java               # Аннотации к текстам 🖋️
│   │   │   │   └── Rating.java                   # Рейтинги ⭐
│   │   │   ├── repository/                        # Spring Data JPA репозитории 📚
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── AlbumRepository.java
│   │   │   │   ├── SongRepository.java
│   │   │   │   ├── AlbumCommentRepository.java
│   │   │   │   ├── SongCommentRepository.java
│   │   │   │   └── RatingRepository.java
│   │   │   ├── service/                           # Сервисный слой 🧪
│   │   │   │   ├── UserService.java
│   │   │   │   └── RatingService.java
│   │   │   └── security/                          # Конфигурация безопасности 🛡️
│   │   │       └── SecurityConfig.java
│   │   └── resources/
│   │       ├── application.properties             # Основная конфигурация 🧙‍♂️
│   │       ├── data.sql                           # Начальные данные (альбомы, песни) 📖
│   │       ├── static/                            # Статические ресурсы 🎨
│   │       │   ├── css/style.css                 # Основные стили 🖌️
│   │       │   ├── images/                       # Изображения (обложки, аватарки) 🖼️
│   │       │   └── js/                           # JavaScript файлы ⚡
│   │       ├── templates/                         # Thymeleaf шаблоны 🏗️
│   │       │   ├── home.html                     # Главная страница 🏚️
│   │       │   ├── album-detail.html             # Страница альбома ⚰️
│   │       │   ├── song-detail.html              # Страница песни 🎶
│   │       │   ├── login.html                    # Вход 🔐
│   │       │   ├── register.html                 # Регистрация 📝
│   │       │   ├── profile.html                  # Профиль пользователя 👤
│   │       │   ├── user-profile.html             # Публичный профиль 💀
│   │       │   ├── search-results.html           # Результаты поиска 🔮
│   │       │   └── error.html                    # Страница ошибки ☠️
│   │       └── db/migration/                      # SQL миграции 📜
│   │           ├── V1__Create_tables.sql
│   │           └── V2__Insert_initial_data.sql
│   └── test/                                      # Тесты 🧪
│       └── java/com/zxc/lyricannotations/service/
│           └── AlbumServiceTest.java             # Пример теста сервиса ⚗️
├── Dockerfile                                     # Конфигурация Docker образа 🐳
├── docker-compose.yml                            # Docker Compose для запуска 🚀
├── pom.xml                                       # Maven конфигурация 🔧
└── README.md
```
🗄️ База данных ⚰️
Управление миграциями 📜
Проект использует SQL миграции через структуру папки db/migration. Для разработки используется data.sql для начального наполнения.

Миграции: 📂
```bash
src/main/resources/db/migration/
├── V1__Create_tables.sql     # Создание всех таблиц ⚙️
└── V2__Insert_initial_data.sql # Базовые данные 📊
```
Основные таблицы 📊
users - зарегистрированные пользователи 🧛

album - музыкальные альбомы ⚰️

song - песни с текстами 🎶

song_comment - комментарии к песням 💬

album_comment - комментарии к альбомам 📝

rating - оценки песен и альбомов (1-5 звёзд) ⭐
Начальные данные 📖
При старте приложения автоматически загружаются:

2 альбома ZXC CURSED ("WAKE UP PARALYZED" и "мир мне должен") ⚰️

28 песен с реальными текстами 🎶

Тестовый пользователь (username: testuser, password: password) 👤

🛠️ Разработка 🧙‍♂️
Backend (Spring Boot) ⚙️
``` bash
# Запуск в режиме разработки с горячей перезагрузкой 🔥
mvn spring-boot:run

# Сборка проекта 🏗️
mvn clean package

# Запуск тестов 🧪
mvn test

# Просмотр зависимостей 📚
mvn dependency:tree
```
Frontend (Thymeleaf + CSS) 🎨
``` bash
# Фронтенд встроен в бэкенд через Thymeleaf
# Шаблоны: src/main/resources/templates/ 🏗️
# Статические файлы: src/main/resources/static/ 🎨
# Стили: src/main/resources/static/css/style.css 🖌️
```
Полезные команды 🛠️
``` bash
# Сброс и перезапуск базы данных (H2) 🔄
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.jpa.hibernate.ddl-auto=create-drop"

# Запуск с профилем production 🚀
mvn spring-boot:run -Dspring.profiles.active=prod

# Создание Docker образа 🐳
docker build -t lyric-annotations:latest .
```
📋 Основные маршруты (Endpoints) 🛣️
Публичные (доступны без авторизации) 🌐
GET / - главная страница 🏚️

GET /album/{id} - страница альбома ⚰️

GET /song/{id} - страница песни 🎶

GET /search - поиск песен 🔮

GET /user/{username} - публичный профиль 💀

GET /login - страница входа 🔐

GET /register - страница регистрации 📝

Защищённые (требуют авторизации) 🛡️
GET /profile - личный профиль 👤

POST /upload-avatar - загрузка аватара 🖼️

POST /update-profile - обновление профиля ✏️

POST /album/{id}/comment - комментарий к альбому 💬

POST /song/{id}/comment - комментарий к песне 📝

POST /album/{id}/rate - оценка альбома ⭐

POST /song/{id}/rate - оценка песни ⭐

POST /album/comment/{id}/delete - удаление комментария к альбому 🗑️

POST /song/comment/{id}/delete - удаление комментария к песне 🗑️

🏗️ Архитектура 🏛️
Backend (Многослойная архитектура) ⚙️
Controllers - обработка HTTP запросов и рендеринг представлений 🎭

Services - бизнес-логика, транзакции 🧪

Repositories - доступ к данным через Spring Data JPA 📚

Entities - доменные модели, отображение таблиц БД 📊

Frontend (Server-Side Rendering) 🎨
Thymeleaf Templates - HTML шаблоны с серверным рендерингом 🏗️

CSS3 - современные стили с готической темой 🖤

Responsive Design - адаптация под мобильные устройства 📱

Vanilla JavaScript - минимальная клиентская логика ⚡

Безопасность 🛡️
Spring Security - аутентификация и авторизация 🔐

BCrypt - хеширование паролей 🔒

CSRF Protection - защита от межсайтовой подделки запросов 🛡️

Session Management - управление сессиями пользователей 👥

📱 Функциональность ⚙️
✅ Реализовано 🎉
Аутентификация и регистрация пользователей 🔐

Просмотр альбомов и песен с полными текстами ⚰️🎶

Система рейтингов (1-5 звёзд) для песен и альбомов ⭐

Комментирование песен и альбомов 💬

Поиск по названиям песен 🔮

Профили пользователей с аватарами и био 👤

Загрузка аватаров через форму 🖼️

Адаптивный дизайн для мобильных устройств 📱

Панель администратора H2 Console для разработки 🛠️

🔍 Тестирование 🧪
Запуск тестов ⚗️
``` bash
# Все тесты 🧪
mvn test

# Конкретный тестовый класс 🔬
mvn test -Dtest=AlbumServiceTest

# С генерацией отчета 📊
mvn test surefire-report:report
```
Интеграционное тестирование 🧩
Проект включает пример теста сервиса:
AlbumServiceTest.java - тестирование бизнес-логики работы с альбомами ⚗️
