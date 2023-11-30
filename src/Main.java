import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        Random rand = new Random(0);
        System.out.print("Введите размер списка: ");
        int n = kb.nextInt();
        List list = new List();
        for (int i = 0; i < n; i++) {
            list.push_back(rand.nextInt(100));
        }
        list.print();
        list.reverse_print();
        list.clear();
        list.print();
        list.reverse_print();

        System.out.print("Введите индекс для добавлениz значения: ");
        int k = kb.nextInt();
        System.out.print("Введите значение: ");
        int b = kb.nextInt();
        list.insert(b, k);
        list.print();

        System.out.print("Введите индекс для удаления значения: ");
        int d = kb.nextInt();
        list.erase(d);
        list.print();
    }
}