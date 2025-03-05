/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

/**
 *
 * @author shuej
 */
import java.util.Scanner;

public class MainMenuUI {

    Scanner scanner = new Scanner(System.in);

    public String getMenuChoice() {
        System.out.println("\nUniversity Management Main Menu");
        System.out.println("1. Student Registration Management");
        System.out.println("2. Course Management");
        System.out.println("3. Tutorial Group Management");
        System.out.println("4. Teaching Assignment");
        System.out.println("5. Assignment Team Management");
        System.out.println("6. Quit");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();
        System.out.println();
        return choice;
    }

}
