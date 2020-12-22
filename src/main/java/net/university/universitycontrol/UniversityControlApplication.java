package net.university.universitycontrol;

import net.university.universitycontrol.services.UniversityService;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class UniversityControlApplication implements CommandLineRunner {
    private static Logger log = LoggerFactory
            .getLogger(UniversityControlApplication.class);

    private UniversityService universityService;

    @Autowired
    public UniversityControlApplication(UniversityService universityService) {
        this.universityService = universityService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UniversityControlApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String departmentName;
        String title;

        System.out.println();
        while (true){
            log.info("Select menu item: ");
            log.info("___________________");
            log.info("1. Get head of department");
            log.info("2. Show department statistics");
            log.info("3. Show department average salary");
            log.info("4. Show department employee count");
            log.info("5. Search");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine();
                    log.info("Write department name to find head of department: ");
                    departmentName = scanner.nextLine();

                    log.info("Result: \n" + universityService.getHeadOfDepartment(departmentName));
                    System.out.println();
                    break;
                case 2:
                    scanner.nextLine();
                    log.info("Write department name to show statistics: ");
                    departmentName = scanner.nextLine();

                    log.info("Result: \n" + universityService.getDepartmentStatistics(departmentName));
                    System.out.println();
                    break;
                case 3:
                    scanner.nextLine();
                    log.info("Write department name to show average salary: ");
                    departmentName = scanner.nextLine();

                    log.info("Result: \n" + universityService.getDepartmentAverageSalary(departmentName));
                    System.out.println();
                    break;
                case 4:
                    scanner.nextLine();
                    log.info("Write department name to show employee count: ");
                    departmentName = scanner.nextLine();

                    log.info("Result: \n" + universityService.getDepartmentEmployeeCount(departmentName));
                    System.out.println();
                    break;
                case 5:
                    scanner.nextLine();
                    log.info("Write title for search: ");
                    title = scanner.nextLine();

                    log.info("Result: \n" + universityService.search(title)) ;
                    System.out.println();
                    break;
                default:
                    log.info("Not a menu selected, exiting");
                    System.exit(1);
            }

        }



    }
}
