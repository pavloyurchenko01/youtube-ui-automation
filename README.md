# Youtube UI Automation (Selenium + TestNG)

### Опис
**Youtube UI Automation** - демонстраційний фреймворк для автоматизованого тестування сайту [YouTube](https://youtube.com).
Фреймворк реалізовано з використанням **Selenium WebDriver**, **TestNG**, **WebDriverManager** та побудовано за паттерном **Page Object Model (POM)**.
Мета проєкту - продемонструвати базові навички побудов UI-автоматизації, структури фреймворку та один e2e-сценарій: пошук запиту → відкриття відео → перевірка поп-апу "Увійти" після кліку "Підписатися".

___

## Структура проєкту

```
youtube-ui-automation/
├── pom.xml                          # Конфігурація Maven
└── src/
    └── test/
        └── java/
            ├── pages/               # Page Object класи
            │   ├── BasePage.java
            │   ├── HomePage.java
            │   ├── ResultPage.java
            |   |── VideoPage.java
            │   └── ChannelPage.java
            ├── tests/               # Тестові класи
            │   ├── BaseTest.java
            │   └── YouTubeTest.java
            └── util/                # Утиліти / фабрики
                └── DriverFactory.java
```

```

## Використані технології

|  Компоненет  |   Призначення   |
|-------------_|-----------------|
| **Java17+**  | мова реалізації |
| **Selenium 4** | UI-автоматизація|
| **TestNG** | фреймворк для тест-ранів |
| **WebDriverManager** | автоматичне керування драйверами браузера |
| **AssertJ** | fluent-asserts |
| **Maven** | білд-система та менеджер залежностей |

```

## Як запустити тести

### 1. Клонувати репозиторій:
```bash
git clone https://github.com/pavloyurchenko01/youtube-ui-automation
cd youtube-ui-automation
```

### 2. Запустити тести:
```bash
mvn clean test
```

### 3. Додаткові параметри запуску:
```bash
mvn clean test -DbaseUrl=https:/youtube.com -Dbrowser=chrome -Dheadless=true
```

> `baseUrl' можна змінювати для різних середовищ
> `browser` підтримує Chrome, Firefox та Edge
> `headless=true` дозволяє запуск без відкриття UI

___

# Архітектура фреймворку

| Клас | Призначення |
|------|-------------|
| **BasePage** | спільна логіка для сторінок (waits, scroll, isClick, `BASE_URL`) |
| **HomePage**| відкриття головної сторінки та введення пошукового запиту |
| **ResultPage** | вибір відео зі сторінки результатів |
| **VideoPage** |  клік на автора відео та перехід на канал |
| **ChanelPage** | дії на сторінці каналу, перевірка поп-апу "Увійти" |
| **DriveFactory** | створення WebDriver з параметрами браузера |
| **BaseTest** | ініціалізація / закриття драйвера|
| **YoutubeTest** | єдиний e2e-тест сценарій для демонстрації флоу |

___

## Автор
**Pavlo Yurchenko**
QA Engineer

