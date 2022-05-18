﻿## Задача 3. Ресторан
#### Описание
Попробуем написать реализацию небольшого ресторанчика, где несколько официантов и иногда заходят посетители. Иногда в ресторан приходят посетители, выбирают блюдо, едят и уходят.

#### Работа программы
1. Создание потока-официанта и потока-посетителя
2. Посетитель заходит в ресторан и, через какое-то время придумывает, что заказать
3. Официант принимает заказ и через некоторое время приносит его
4. Поток-посетитель завершает свою работу с задержкой, эмулируя таким образом, прием пищи.
#### Требования к программе
1. Каждый ключевой этап должен сопровождаться выводом в консоль текущего статуса, например: Официант1 понес заказ Посетителю2
2. Все задержки (время приготовления блюда, таймаут захода посетителей) должны быть оформлены в константах (никаких "Магических чисел")
3. Ресторан должен обслужить 5 посетителей и закрыться

