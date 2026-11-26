package ru.zotov.main;

import ru.zotov.bird.Bird;
import ru.zotov.bird.Cuckoo;
import ru.zotov.bird.Parrot;
import ru.zotov.bird.Sparrow;
import ru.zotov.city.City;
import ru.zotov.city.Route;
import ru.zotov.city.TwoWayCity;
import ru.zotov.point.Point;
import ru.zotov.point.Point3D;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class Main {

    public static double power(String xStr, String yStr) {
        int x = parseInt(xStr);
        int y = parseInt(yStr);
        return pow(x, y);
    }
    public void pressEnterToContinue(Scanner scanner) {
        System.out.print("\n\u001B[90m[ Нажмите Enter, чтобы продолжить... ]\u001B[0m");
        scanner.nextLine();
        System.out.println();
    }

    void numOne(Scanner scanner){
        System.out.println("  ── ЗАДАЧИ 1, 2 и 3: Города, Маршруты и Двусторонние дороги ──");

        Map<String, City> cities = new HashMap<>();
        boolean subMenuRunning = true;

        while (subMenuRunning) {
            System.out.println("\n  ┌── УПРАВЛЕНИЕ ГОРОДАМИ ──────────┐");
            System.out.println("  │ 1. Добавить обычный город       │");
            System.out.println("  │ 2. Добавить двусторонний город  │");
            System.out.println("  │ 3. Удалить город                │");
            System.out.println("  │ 4. Добавить дорогу              │");
            System.out.println("  │ 5. Посмотреть все города        │");
            System.out.println("  │ 6. Проложить маршрут            │");
            System.out.println("  │ 0. Назад в главное меню         │");
            System.out.println("  └─────────────────────────────────┘");
            System.out.print("  ➔ Выбор: ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }
            int subChoice = scanner.nextInt();
            scanner.nextLine();

            switch (subChoice) {
                case 1:
                case 2: {
                    System.out.print("  ➔ Введите название нового города: ");
                    String name = scanner.nextLine().trim();
                    System.out.println("  ────────────────────────────────────────────────────");
                    if (cities.containsKey(name)) {
                        System.out.println("  [!] Город с таким названием уже существует.");
                    } else {

                        if (subChoice == 1) {
                            cities.put(name, new City(name));
                            System.out.println("  [+] Обычный город " + name + " успешно добавлен.");
                        } else {
                            cities.put(name, new TwoWayCity(name));
                            System.out.println("  [+] Двусторонний город " + name + " успешно добавлен.");
                        }
                    }
                    System.out.println("  ────────────────────────────────────────────────────");
                    break;
                }
                case 3: {
                    System.out.print("  ➔ Введите название города для удаления: ");
                    String name = scanner.nextLine().trim();
                    System.out.println("  ────────────────────────────────────────────────────");
                    City cityToRemove = cities.get(name);

                    if (cityToRemove != null) {
                        cities.remove(name);
                        for (City city : cities.values()) {
                            city.removeRoad(cityToRemove);
                        }
                        System.out.println("  [-] Город " + name + " удален со всеми ведущими к нему дорогами.");
                    } else {
                        System.out.println("  [!] Город не найден.");
                    }
                    System.out.println("  ────────────────────────────────────────────────────");
                    break;
                }
                case 4: {
                    System.out.print("  ➔ Откуда (название города): ");
                    String from = scanner.nextLine().trim();
                    System.out.print("  ➔ Куда (название города): ");
                    String to = scanner.nextLine().trim();

                    if (cities.containsKey(from) && cities.containsKey(to)) {
                        System.out.print("  ➔ Стоимость поездки: ");
                        while (!scanner.hasNextInt()) {
                            System.out.print("  [!] Введите целое число: ");
                            scanner.next();
                        }
                        int cost = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("  ────────────────────────────────────────────────────");

                        cities.get(from).addRoad(cities.get(to), cost);

                        System.out.println("  [+] Дорога добавлена!");
                    } else {
                        System.out.println("  ────────────────────────────────────────────────────");
                        System.out.println("  [!] Ошибка: один или оба города не существуют.");
                    }
                    System.out.println("  ────────────────────────────────────────────────────");
                    break;
                }
                case 5: {
                    System.out.println("  ────────────────────────────────────────────────────");
                    System.out.println("\n  Список городов:");
                    if (cities.isEmpty()) {
                        System.out.println("    (пусто)");
                    } else {
                        for (City city : cities.values()) {
                            String type = (city instanceof TwoWayCity) ? "[Д]" : "[О]";
                            System.out.println("    " + type + " " + city);
                        }
                    }
                    System.out.println("  ────────────────────────────────────────────────────");
                    break;
                }
                case 6: {
                    System.out.print("  ➔ Точка отправления: ");
                    String startName = scanner.nextLine().trim();
                    System.out.print("  ➔ Точка назначения: ");
                    String endName = scanner.nextLine().trim();
                    System.out.println("  ────────────────────────────────────────────────────");
                    if (cities.containsKey(startName) && cities.containsKey(endName)) {
                        try {
                            City start = cities.get(startName);
                            City end = cities.get(endName);

                            Route route = new Route(start, end);

                            System.out.println("\n  ➔ Маршрут:");
                            System.out.println("    " + route);

                        } catch (IllegalArgumentException e) {
                            System.out.println("  [!] Ошибка: " + e.getMessage());
                        }
                    } else {
                        System.out.println("  [!] Ошибка: один или оба города не существуют.");
                    }
                    System.out.println("  ────────────────────────────────────────────────────");
                    break;
                }
                case 0: {
                    subMenuRunning = false;
                    break;
                }
                default:
                    System.out.println("  ────────────────────────────────────────────────────");
                    System.out.println("  [!] Неверный пункт меню.");
                    System.out.println("  ────────────────────────────────────────────────────");
            }
        }
    }

    void numTwo(Scanner scanner){
        System.out.println("  ── ЗАДАЧА 4: Трехмерная точка ──");
        System.out.println("  [ Ввод координат для 3-х точек ]");

        Point3D[] points = new Point3D[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("  ➔ Точка " + (i + 1) + ":");

            System.out.print("     Координата X: ");
            while (!scanner.hasNextInt()) {
                System.out.print("     [!] Введите целое число: ");
                scanner.next();
            }
            int x = scanner.nextInt();

            System.out.print("     Координата Y: ");
            while (!scanner.hasNextInt()) {
                System.out.print("     [!] Введите целое число: ");
                scanner.next();
            }
            int y = scanner.nextInt();

            System.out.print("     Координата Z: ");
            while (!scanner.hasNextInt()) {
                System.out.print("     [!] Введите целое число: ");
                scanner.next();
            }
            int z = scanner.nextInt();

            points[i] = new Point3D(x, y, z);
        }
        scanner.nextLine();
        System.out.println("  ────────────────────────────────────────────────────");
        System.out.println("  Результат (Текстовое представление 3D точек):");
        for (int i = 0; i < points.length; i++) {
            System.out.println("    Точка " + (i + 1) + ": " + points[i]);
        }
        System.out.println("  ────────────────────────────────────────────────────");

    }

    void numThree() {
        System.out.println("  ── ЗАДАЧА 5: Птицы ──");

        Bird sparrow = new Sparrow();
        Bird cuckoo = new Cuckoo();
        Bird parrot = new Parrot("Россия — священная наша держава, Россия — любимая наша страна!");

        System.out.println("  ────────────────────────────────────────────────────");

        System.out.print("  ➔ Воробей: ");
        sparrow.sing();

        System.out.print("  ➔ Кукушка: ");
        cuckoo.sing();

        System.out.print("  ➔ Попугай: ");
        parrot.sing();

        System.out.println("  ────────────────────────────────────────────────────");
    }
    void numFour(){

        System.out.println("  ── ЗАДАЧА 6: Сравнение городов (equals по набору путей) ──");

        System.out.println("  [ Создаем пункты назначения... ]");
        City moscow = new City("Москва");
        City spb = new City("СПБ");

        System.out.println("  [ Создаем Город А (Обычный) и Город Б (Двусторонний)... ]");
        City cityA = new City("Город А");
        TwoWayCity cityB = new TwoWayCity("Город Б");


        cityA.addRoad(moscow, 1500);
        cityA.addRoad(spb, 2000);

        cityB.addRoad(moscow, 1500);
        cityB.addRoad(spb, 2000);

        System.out.println("  ────────────────────────────────────────────────────");
        System.out.println("    " + cityA.getClass().getSimpleName() + ": " + cityA);
        System.out.println("    " + cityB.getClass().getSimpleName() + ": " + cityB);

        System.out.println("  ➔ cityA.equals(cityB) : " + cityA.equals(cityB));

        System.out.println("\n  [ Меняем стоимость пути в Городе Б... ]");
        cityB.addRoad(moscow, 9999);
        System.out.println("    " + cityB.getClass().getSimpleName() + ": " + cityB);

        System.out.println("  ➔ cityA.equals(cityB): " + cityA.equals(cityB));

        System.out.println("  ────────────────────────────────────────────────────");
    }
    void numFive(String[] args) {
        System.out.println("  ── ЗАДАЧА 7: Возведение в степень (Статический импорт) ──");
        if (args.length >= 2) {
            String x = args[0];
            String y = args[1];
            double result = power(x, y);
            System.out.println("  ➔ Параметры из командной строки: X = " + x + ", Y = " + y);
            System.out.println("  ➔ Результат возведения в степень: " + result);
        } else {
            System.out.println("  [!] Передайте два аргумента командной строки (например: число X и Y).");
            System.out.println("      Пример запуска через терминал: java ru.zotov.main.Main 2 3");
        }
        System.out.println("  ────────────────────────────────────────────────────");
    }
    void numSix() {
        System.out.println("  ── ЗАДАЧА 8: Клонирование точки ──");
        Point original = new Point(10, 20);
        Point clone = original.clone();

        System.out.println("    Оригинальная точка: " + original);
        System.out.println("    Клонированная точка: " + clone);
        System.out.println("    Ссылки различаются (original != clone): " + (original != clone));
        System.out.println("  ────────────────────────────────────────────────────");
    }
     void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("\n");
            System.out.println("  ┌───────────────────────────────────────────┐");
            System.out.println("  │     ЛАБОРАТОРНАЯ РАБОТА №3 • ВАРИАНТ 5    │");
            System.out.println("  └───────────────────────────────────────────┘");
            System.out.println("    [1] Задание н1 (10) и н2 (5) и н3 (3) и н5(9): Города и Маршруты и Двусторонняя дорога и Городим"); // Сделано достаточно чтобы любая из 4 задач была решена, для городим просто введем граф с клавы
            System.out.println("    [2] Задание н3 (5) 3D Точка");
            System.out.println("    [3] Задание н4 (3) Птицы");
            System.out.println("    [4] Задание н6 (5) Сравнение городов");
            System.out.println("    [5] Задание н7 (3) Возведение в степень");
            System.out.println("    [6] Задание 8: Клонирование точки");
            System.out.println("    [0] Выход");
            System.out.println("  ──────────────────────────────────────────");
            System.out.print("  ➔ Выберите номер задачи: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            try {
                switch (choice) {
                    case 1:{
                        numOne(scanner);
                        break;
                    }
                    case 2: {
                        numTwo(scanner);
                        break;
                    }
                    case 3: {
                        numThree();
                        break;
                    }
                    case 4: {
                        numFour();
                        break;
                    }
                    case 5: {
                        numFive(args);
                        break;
                    }
                    case 6: {
                        numSix();
                        break;
                    }
                    default:
                        System.out.println("  [Ошибка] Неверный пункт меню.");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("\n  [Ошибка] Произошла ошибка: " + ex.getMessage());
            }

            pressEnterToContinue(scanner);
        }
    }
}