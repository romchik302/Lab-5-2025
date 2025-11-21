import functions.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InappropriateFunctionPointException, IOException {

        // Создаем тестовые данные с другими числами
        double[] testValues1 = {2.5, 6.1, 12.8, 20.3, 30.7};

        // Создаем объекты для тестирования
        ArrayTabulatedFunction arrayFunc1 = new ArrayTabulatedFunction(1.0, 5.0, testValues1);
        ArrayTabulatedFunction arrayFunc2 = new ArrayTabulatedFunction(1.0, 5.0, testValues1);
        LinkedListTabulatedFunction listFunc1 = new LinkedListTabulatedFunction(1.0, 5.0, testValues1);
        LinkedListTabulatedFunction listFunc2 = new LinkedListTabulatedFunction(1.0, 5.0, testValues1);

        System.out.println("=== ТЕСТИРОВАНИЕ ПЕРЕОПРЕДЕЛЕННЫХ МЕТОДОВ ===\n");

        // 1. Тестирование метода toString()
        System.out.println("1. Тест метода toString():");
        System.out.println("ArrayTabulatedFunction: " + arrayFunc1.toString());
        System.out.println("LinkedListTabulatedFunction: " + listFunc1.toString());
        System.out.println();

        // 2. Тестирование метода equals()
        System.out.println("2. Тест метода equals():");
        System.out.println("arrayFunc1 equals arrayFunc2: " + arrayFunc1.equals(arrayFunc2));
        System.out.println("listFunc1 equals listFunc2: " + listFunc1.equals(listFunc2));
        System.out.println("arrayFunc1 equals listFunc1: " + arrayFunc1.equals(listFunc1));
        System.out.println("listFunc1 equals arrayFunc1: " + listFunc1.equals(arrayFunc1));
        System.out.println();

        // 3. Тестирование метода hashCode()
        System.out.println("3. Тест метода hashCode():");
        System.out.println("Хэш arrayFunc1: " + arrayFunc1.hashCode());
        System.out.println("Хэш arrayFunc2: " + arrayFunc2.hashCode());
        System.out.println("Хэш listFunc1: " + listFunc1.hashCode());
        System.out.println("Хэш listFunc2: " + listFunc2.hashCode());
        System.out.println("Согласованность equals/hashCode для массивов: " +
                (arrayFunc1.equals(arrayFunc2) == (arrayFunc1.hashCode() == arrayFunc2.hashCode())));
        System.out.println("Согласованность equals/hashCode для списков: " +
                (listFunc1.equals(listFunc2) == (listFunc1.hashCode() == listFunc2.hashCode())));
        System.out.println();

        // 4. Тестирование изменения хэш-кода при модификации
        System.out.println("4. Тест изменения хэш-кода:");
        int originalListHash = listFunc1.hashCode();
        FunctionPoint modifiedPoint = new FunctionPoint(3.0, 50.0);
        listFunc1.setPoint(2, modifiedPoint);
        int modifiedListHash = listFunc1.hashCode();
        System.out.println("Исходный хэш списка: " + originalListHash);
        System.out.println("Измененный хэш списка: " + modifiedListHash);
        System.out.println("Хэш изменился после модификации: " + (originalListHash != modifiedListHash));
        System.out.println();

        // 5. Тестирование метода clone()
        System.out.println("5. Тест метода clone():");
        ArrayTabulatedFunction arrayClone = (ArrayTabulatedFunction) arrayFunc1.clone();
        LinkedListTabulatedFunction listClone = (LinkedListTabulatedFunction) listFunc1.clone();

        System.out.println("arrayFunc1 == arrayClone: " + (arrayFunc1 == arrayClone));
        System.out.println("listFunc1 == listClone: " + (listFunc1 == listClone));
        System.out.println("arrayFunc1 equals arrayClone: " + arrayFunc1.equals(arrayClone));
        System.out.println("listFunc1 equals listClone: " + listFunc1.equals(listClone));

        // Проверка глубокого клонирования для массива
        double originalArrayY = arrayFunc1.getPoint(1).getY();
        arrayFunc1.getPoint(1).setY(999.9);
        System.out.println("Глубокое клонирование массива: " +
                (arrayClone.getPoint(1).getY() != 999.9 && arrayClone.getPoint(1).getY() == originalArrayY));

        // Проверка глубокого клонирования для списка
        double originalListY = listFunc1.getPoint(1).getY();
        listFunc1.getPoint(1).setY(888.8);
        System.out.println("Глубокое клонирование списка: " +
                (listClone.getPoint(1).getY() != 888.8 && listClone.getPoint(1).getY() == originalListY));

        System.out.println("\n=== ТЕСТИРОВАНИЕ ЗАВЕРШЕНО ===");
    }
}