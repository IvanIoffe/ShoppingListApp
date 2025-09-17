# ShoppingListApp

![Kotlin](https://img.shields.io/badge/language-Kotlin-blue.svg)
![Platform](https://img.shields.io/badge/platform-Android-green.svg)
![Status](https://img.shields.io/badge/version-alpha-orange.svg)

ShoppingListApp — современное Android-приложение для создания и управления списками покупок. Проект написан на языке Kotlin, реализует современные подходы к архитектуре и использует популярные библиотеки и инструменты Android-разработки.  
Внимание: приложение находится в активной разработке

## 🚀 Основные функции

- **Вход по специальному ключу**  
  При входе в приложение введите специальный ключ для доступа к спискам покупок.(для тестов - CCE9R1)

- **Создание и редактирование списков покупок**  
  Быстро формируйте отдельные списки для разных магазинов и целей.

- **Добавление и удаление товаров**  
  Указывайте название и количество товара.

- **Работа офлайн**  
  Все данные хранятся на устройстве и доступны даже без интернета.

- **Интуитивный интерфейс**  
  Современный дизайн на базе Jetpack Compose с реализацией светлой и темной темы.

## 🛠️ Технологии

- Kotlin — основной язык разработки
- Jetpack Compose — декларативный UI
- Clean Architecture — разделение слоёв приложения
- MVVM — паттерн проектирования
- Coroutines, Flow — асинхронность и реактивность
- Retrofit — сетевые запросы
- Room — локальная база данных
- DataStore — хранение настроек и небольших данных
- Hilt — внедрение зависимостей
- Kotlinx Serialization — сериализация данных

## 📦 Установка и запуск

1. Клонируйте репозиторий:
   git clone https://github.com/IvanIoffe/ShoppingListApp.git
2. Откройте проект в Android Studio

3. Соберите и запустите приложение на эмуляторе или реальном устройстве.

> Требования:
> - Android SDK 26+
> - Kotlin 2.2.0+

## 🖼️ Скриншоты

**Экран входа**
<p align="center">
  <img alt="Light" src="https://private-user-images.githubusercontent.com/171175248/490485712-d025d199-6443-4ce4-8187-24af0356c31f.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgxMDQxNDQsIm5iZiI6MTc1ODEwMzg0NCwicGF0aCI6Ii8xNzExNzUyNDgvNDkwNDg1NzEyLWQwMjVkMTk5LTY0NDMtNGNlNC04MTg3LTI0YWYwMzU2YzMxZi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxN1QxMDEwNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1mNjIxZmQxNzQzY2MwMDk3YTdiN2UwNmViZGRhMzk0YzUwZWU0Y2RlMjg3MTg3NjY3ZjcxZDkzOTdhMDg4MjIzJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.2AXyeh8sOerIDSqFHnsj-5_VUti4rTUQwJOYHLZzfL0" width="45%">
&nbsp; &nbsp; &nbsp; &nbsp;
  <img alt="Dark" src="https://private-user-images.githubusercontent.com/171175248/490485709-b0715c1f-d0db-4d3b-af78-6bb247eca64e.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgxMDQxNDQsIm5iZiI6MTc1ODEwMzg0NCwicGF0aCI6Ii8xNzExNzUyNDgvNDkwNDg1NzA5LWIwNzE1YzFmLWQwZGItNGQzYi1hZjc4LTZiYjI0N2VjYTY0ZS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxN1QxMDEwNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT01ODhiOTVmYjdkZTM3MDcxOTliNzM5ZDliOGRhZjhhZjUyY2ZmN2UzMTA1N2I3OWRhZGJkNjQ0ZTc5ZmE5NjAyJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.1m13J5B_v-tKcfs1Scq2FHKNeWIVnuDb_9gCDFujO-c" width="45%">
</p>

**Экран списков покупок**
<p align="center">
  <img alt="Light" src="https://private-user-images.githubusercontent.com/171175248/490485708-d60d7db0-1d1a-47de-8fa5-3e216528c153.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgxMDQxNDQsIm5iZiI6MTc1ODEwMzg0NCwicGF0aCI6Ii8xNzExNzUyNDgvNDkwNDg1NzA4LWQ2MGQ3ZGIwLTFkMWEtNDdkZS04ZmE1LTNlMjE2NTI4YzE1My5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxN1QxMDEwNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1mYTUxYzJkY2Y4MDBhZWVjNzAxMjZhZDYyYThiNzBjNzVjMGU5MzdkMzM0MmVkZTkyMjQ2MzUyYjdhZGMzNzI4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.Zy8irADCWS3V10jG80dfCBg4xvH6QVGif4ZevYRlUdQ" width="45%">
&nbsp; &nbsp; &nbsp; &nbsp;
  <img alt="Dark" src="https://private-user-images.githubusercontent.com/171175248/490485711-4e833fcb-80bb-435c-a768-dcd4bddaed5e.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgxMDQxNDQsIm5iZiI6MTc1ODEwMzg0NCwicGF0aCI6Ii8xNzExNzUyNDgvNDkwNDg1NzExLTRlODMzZmNiLTgwYmItNDM1Yy1hNzY4LWRjZDRiZGRhZWQ1ZS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxN1QxMDEwNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zNTcyNjYwZGU4OTU4MGIzMTRkZjcyMzMzODcyMGE5YzU1YmVjZjczMTgxZWRkMmYyMWRlYjg1YjNiNmIxODc4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.wa89S4L2-YBksh0UiGs0PotvZWzphi97CsCTIN53VTw" width="45%">
</p>

**Экран создания списка покупок**
<p align="center">
  <img alt="Light" src="https://private-user-images.githubusercontent.com/171175248/490485713-b005f6e4-96a5-4f5b-8725-b785d3685553.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgxMDQxNDQsIm5iZiI6MTc1ODEwMzg0NCwicGF0aCI6Ii8xNzExNzUyNDgvNDkwNDg1NzEzLWIwMDVmNmU0LTk2YTUtNGY1Yi04NzI1LWI3ODVkMzY4NTU1My5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxN1QxMDEwNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1hOTMwOTljZGMzYTVlZTA4YTZhNGI4MDUxMDgzNGQ4YmQyMjRjYjYyZTgyZjkzYjU0NGIzNTA4N2ZkZWRmMGM4JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.OQCPlNnra1AcpylGRMq64J-pG_CaV78_7BzgtpAx1TE" width="45%">
&nbsp; &nbsp; &nbsp; &nbsp;
  <img alt="Dark" src="https://private-user-images.githubusercontent.com/171175248/490485703-8b7139b0-5076-4cb7-9873-be8f23f036e3.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgxMDQxNDQsIm5iZiI6MTc1ODEwMzg0NCwicGF0aCI6Ii8xNzExNzUyNDgvNDkwNDg1NzAzLThiNzEzOWIwLTUwNzYtNGNiNy05ODczLWJlOGYyM2YwMzZlMy5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxN1QxMDEwNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xZmQ5NTRmNjI3ODkzMDY4ZmI1NjlhM2E3MDZiNTU5YzA5ZGYwYzJmNDk3ZDRiNzhlODRkZTZhNGY3NWJhZTNjJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.AwEf4rgRtQlSLwc_0uuzOkJPqf6U4D79IpNVIPD2imA" width="45%">
</p>

**Экран списка товаров**
<p align="center">
  <img alt="Light" src="https://private-user-images.githubusercontent.com/171175248/490485710-a3ea595f-5a4c-497c-973b-b3d8acaa6d6e.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgxMDQxNDQsIm5iZiI6MTc1ODEwMzg0NCwicGF0aCI6Ii8xNzExNzUyNDgvNDkwNDg1NzEwLWEzZWE1OTVmLTVhNGMtNDk3Yy05NzNiLWIzZDhhY2FhNmQ2ZS5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxN1QxMDEwNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1mMmMwNzY3MDg2YjAxZGIzYzVjNjAzNzQ5YTYzODMyNjAwYzk0YzQyMWJmZTZjMmNiNGQwOGJhMjIwNTlmZDQxJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.kHIXCUXHDmFe_OSAukMCi7hPnB7WuDRsSWJmThdxzU8" width="45%">
&nbsp; &nbsp; &nbsp; &nbsp;
  <img alt="Dark" src="https://private-user-images.githubusercontent.com/171175248/490485705-f74f25eb-8574-4b96-a532-6109c31151bd.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgxMDQxNDQsIm5iZiI6MTc1ODEwMzg0NCwicGF0aCI6Ii8xNzExNzUyNDgvNDkwNDg1NzA1LWY3NGYyNWViLTg1NzQtNGI5Ni1hNTMyLTYxMDljMzExNTFiZC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxN1QxMDEwNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0xYjdiZDkyZTkxZDhiMTZkNTY3YzdiYWUwZWQwNTQ3MDE2YTU4YmEzNWRjMmQ4ODkyODNmZDYxYzc4NTI5M2QyJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.lISESDowiTzqzPA4pbmZ6Cll1WGFie3UvA-MQU5gCMc" width="45%">
</p>

**Экран добавления товара в список**
<p align="center">
  <img alt="Light" src="https://private-user-images.githubusercontent.com/171175248/490485704-a0d0ab8d-9e2c-49b5-9d26-44614581790b.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgxMDQxNDQsIm5iZiI6MTc1ODEwMzg0NCwicGF0aCI6Ii8xNzExNzUyNDgvNDkwNDg1NzA0LWEwZDBhYjhkLTllMmMtNDliNS05ZDI2LTQ0NjE0NTgxNzkwYi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxN1QxMDEwNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1hNjgyMjhiM2UxN2Q4ZDI3M2M2MDU4NjMxNTQwOTljZDlkYTRiYmY2M2MzMjhkNzc5YjU4YTBhYTNhODIxMWMyJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.kUULSxCjclo-aQmyBYQALPRY6VZ8gMI4TTIACc2MZsw" width="45%">
&nbsp; &nbsp; &nbsp; &nbsp;
  <img alt="Dark" src="https://private-user-images.githubusercontent.com/171175248/490485706-22f5c86d-ca4d-4ece-bd67-795826d72d72.png?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NTgxMDQxNDQsIm5iZiI6MTc1ODEwMzg0NCwicGF0aCI6Ii8xNzExNzUyNDgvNDkwNDg1NzA2LTIyZjVjODZkLWNhNGQtNGVjZS1iZDY3LTc5NTgyNmQ3MmQ3Mi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUwOTE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MDkxN1QxMDEwNDRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT00NWQxNzIyMjI2M2UzNjBkYjYyYTUzZGViZTcxZWNjODMxZDliMzQwZGQwNDU0OGM2NmE1ZTMxZWQ2MjViZThjJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.kgD5ygPAG5-iVOAjkBidDHaWIAWmIXhhjRGekphjlao" width="45%">
</p>

## 🤝 Вклад в проект

Будем рады вашим идеям и pull request’ам!
- Откройте issue, если нашли ошибку или хотите предложить улучшение.
- Форкните репозиторий, создайте новую ветку и отправьте pull request.

---

Автор: [IvanIoffe](https://github.com/IvanIoffe)

> *Если вам понравилось приложение — ставьте ⭐ и делитесь с друзьями!*