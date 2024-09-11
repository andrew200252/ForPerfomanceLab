
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Необходимо передать имя файла в качестве аргумента командной строки.");
            System.exit(1);
        }

        String fileName = args[0];
        ArrayList<Integer> nums = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                nums.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("Файл не найден.");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("Файл содержит неверные данные.");
            System.exit(1);
        }

        int result = minMoves(nums);
        System.out.println(result);
    }

    private static int minMoves(ArrayList<Integer> nums) {
        int totalMoves = 0;
        Collections.sort(nums);
        int median = nums.get(nums.size() / 2);
        for (int num : nums) {
            totalMoves += Math.abs(num - median);
        }
        return totalMoves;
    }
}