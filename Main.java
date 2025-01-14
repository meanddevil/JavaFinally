import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    String brand;
    int ram;
    int hdd;
    String os;
    String color;
    
    public Laptop(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }
    
   @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class Main
{
        public static void main(String[] args) {
        //Set: коллекция элементов, не допускающих дублирования.
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("HP", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("Apple", 8, 256, "MacOS", "Silver"));
        laptops.add(new Laptop("Lenovo", 32, 1024, "Windows", "Gray"));
        laptops.add(new Laptop("HP", 4, 1024, "withoutOS", "Black"));
        laptops.add(new Laptop("Honor MagicBook 16", 16, 512, "Windows", "Gray" ));
        
        Map<String, Object> filters = new HashMap<>();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите необходимый критерий:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("0 - Завершить выбор");
        
        int UserChoice;
        while (true) {
            //Функция nextInt класса Scanner считывает 
            //цифры, вводимые с клавиатуры и преобразует их в целое число.
            UserChoice = scanner.nextInt();
            if (UserChoice == 0) {
                break;
            }
            switch (UserChoice) {
                case 1:
                    System.out.println("Минимальный объем ОЗУ?");
                    filters.put("ram", scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Минимальный объем ЖД?");
                    filters.put("hdd", scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Операционная система?");
                    filters.put("os", scanner.next());
                    break;
                case 4:
                    System.out.println("Цвет?");
                    filters.put("color", scanner.next());
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
        
        Set<Laptop> filterLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("ram", 0) instanceof Integer && laptop.ram >= (int) filters.getOrDefault("ram", 0))
                .filter(laptop -> filters.getOrDefault("hdd", 0) instanceof Integer && laptop.hdd >= (int) filters.getOrDefault("hdd", 0))
                .filter(laptop -> filters.getOrDefault("os", "").equals("") || laptop.os.equalsIgnoreCase((String) filters.getOrDefault("os", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("") || laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());
                
                
        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : filterLaptops) {
            System.out.println(laptop);
        }
    }
}
