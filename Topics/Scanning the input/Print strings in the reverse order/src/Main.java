//put imports you need here

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
        List<String> list = Stream.of(new Scanner(System.in).nextLine().split("\\n+"))
                .collect(Collectors.toList());

        Collections.reverse(list);
        list.forEach(System.out::println);
    }
}