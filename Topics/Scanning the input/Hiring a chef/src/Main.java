import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int yearsOfExperience = Integer.parseInt(scanner.nextLine());
        String cousinPreference = scanner.nextLine();

        new Application(name, cousinPreference, yearsOfExperience);
    }
}

class Application {
    private String firstName;
    private String cousinPreference;
    private int yearsOfExperience;

    public Application(String firstName, String cousinPreference, int yearsOfExperience) {
        this.firstName = firstName;
        this.cousinPreference = cousinPreference;
        this.yearsOfExperience = yearsOfExperience;
        reply();
    }

    private void reply() {
        System.out.printf("The form for %s is completed. We will contact you if we need a chef who " +
                "cooks %s dishes and has %d years of experience.\n", firstName, cousinPreference, yearsOfExperience);
    }
}
