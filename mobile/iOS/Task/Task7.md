#  Задача 7 | Корзина – логика отображения и работы экрана

[⬅️ назад](../README.md)

## ТЗ

Необходимо реализовать логику работы корзины

#### Общая логика (Presenter)

1. При открытии экрана 
    1. Получаем данные
        1. Берем содержимое корзины из ICartService
        2. Получаем отсутсвующие данные из IBonusesService + IUserInfoService 
    2. Подготавливаем список товаров к отображению (products: [CartProductViewModel])
    3. Рассчитываем стоимость. Складываем все веса товаров и их цены
    4. Рассчитываем бонус (лучше в отдельный сервис вынести и его протестировать)
    5. Конфигурируем отображение (configureBonusView, configureTotalView)
2. Удаление элемента
    1. Обновляем общее хранилище корзины
    2. Переконфигурируем отображение и пересчитываем бонусы и итог по товарам
    3. Если товаров уже нет – закрываем текущий экран
3. Нажатие на кнопку "Купить"
    1. Вызываем fake сервис, куда передаем корзину (айдишники товаров и итоговую сумму по покупке)
    2. Переход на экран оплаты

#### ICalculateCartService

3. Расчет веса
    1. Для каждого товара сначала учитываем тип (kilo или gramm)
    2. Умножаем вес товара на количество. Расчеты веса в граммах
    3. Складываем все веса

3. Расчет стоимости
    1. Для каждого товара берем количество и стоимость
    2. Складываем всю стоимость товаров
    
4. Расчет бонуса
    1. Для каждого товара берем бонус, если он есть
    2. Применяем бонус, если это кэшбэк – получаем число рублей, которое вернется
    3. Для любимых товаров
        1. Если есть бонус
            1. Умножаем на 1.2 значение бонуса
        2. Если нет бонуса. Тогда в зависимости от активности пользователя: 
            1. 0-25: доп. кэшбэк 0%
            2. 26-50: доп. кэшбэк 2%
            3. 51-75: доп. кэшбэк 3%
            4. 76-100: доп. кэшбэк 5% 
    4. Складываем по всем товарам кэшбэк и баллы

#### IProductViewModelFactory

2. Список товаров (makeCartViewModel)
    1. totalPrice » итоговая сумма по товару (цена на количество). В формате "1 500 ₽"
    2. priceDescription » "%количество% шт. (по %вес%)* %цена за единицу%"
        1. Вес определяется как itemCountity.value + единица изменерия. Единица изменерия определяется по следующей логике
            - kilo – пишем "кг"
            - gamm
                - пишем "г", если вес единицы до 1000
                - пишем "кг", если вес единицы >= 1000
    3. totalWeight » общий вес + " " + единица измерения
        1. Общий вес = количество товаров, на вес одного. Если ОДНА единица товара весит больше 1000 грамм, то нужно общий вес считать в кг
        2. Единица измерения вычисляется по аналогичной логике выше
    4. изображение определяем по imageId
5. Получение остальных ViewModels
    1. makeTotalCartViewModel
        1. цена » В формате "1 500 ₽"
        2. вес » вес + " " + единица измерения
            - если вес >= 1000, то приводим к килограммам. Единица измерения – кг
            - если вес < 1000, то оставляем значение как есть и Единица измерения – г
    2. makeBonusViewModel
        - для кэшбэка в формате "1 500 ₽"
        - для бонусов в формате "1 000 баллов"

## Ожидаемое решение

- Необходимо реализовать `ICalculateCartService`, который менеджерит общую логику корзины
- ICartPresenter
- `IProductViewModelFactory` – метод `makeTotalCartViewModel`, `makeBonusViewModel`, `makeCartViewModel`
