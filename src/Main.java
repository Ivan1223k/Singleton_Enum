import java.util.ArrayList;
import java.util.List;

// Задача 1: Singleton для базы данных
class Database {
    private static Database instance;

    private Database() {
        System.out.println("Создано подключение к базе данных");
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}

// Задача 2: Singleton для логирования
class Logger {
    private static Logger instance;
    private List<String> logs = new ArrayList<>();

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(message);
    }

    public void showLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}

// Задача 3: Enum для статусов заказа и класс Order
enum OrderStatus {
    NEW, IN_PROGRESS, DELIVERED, CANCELLED
}

class Order {
    private int id;
    private OrderStatus status;

    public Order(int id) {
        this.id = id;
        this.status = OrderStatus.NEW;
    }

    public void changeStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.DELIVERED && newStatus == OrderStatus.CANCELLED) {
            System.out.println("Ошибка: нельзя отменить доставленный заказ.");
            return;
        }
        this.status = newStatus;
        System.out.println("Статус заказа #" + id + " изменён на " + status);
    }

    public void showStatus() {
        System.out.println("Заказ #" + id + " в статусе: " + status);
    }
}

// Задача 4: Enum для сезонов и метод получения названия
enum Season {
    WINTER, SPRING, SUMMER, AUTUMN
}

class SeasonHelper {
    public static String getSeasonName(Season season) {
        switch (season) {
            case WINTER:
                return "Зима";
            case SPRING:
                return "Весна";
            case SUMMER:
                return "Лето";
            case AUTUMN:
                return "Осень";
            default:
                return "Неизвестный сезон";
        }
    }
}


public class Main {
    public static void main(String[] args) {

        // Задача 1: Singleton для базы данных
        Database db1 = Database.getInstance();
        Database db2 = Database.getInstance();
        System.out.println("db1 == db2: " + (db1 == db2)); // Должно вывести true

        // Задача 2: Singleton для логирования
        Logger logger = Logger.getInstance();
        logger.log("Программа запущена");
        logger.log("Создана база данных");
        logger.showLogs();

        // Задача 3: Enum для статусов заказа и класс Order
        Order order1 = new Order(101);
        order1.showStatus();
        order1.changeStatus(OrderStatus.IN_PROGRESS);
        order1.changeStatus(OrderStatus.DELIVERED);
        order1.changeStatus(OrderStatus.CANCELLED);

        // Задача 4: Enum для сезонов и метод получения названия
        System.out.println("Весна: " + SeasonHelper.getSeasonName(Season.SPRING));
        System.out.println("Зима: " + SeasonHelper.getSeasonName(Season.WINTER));
    }
}