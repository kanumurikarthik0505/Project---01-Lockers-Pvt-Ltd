import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class LockerPvtLtd {
    static String DIRECTORY;
    File folder_name;

    public LockerPvtLtd() {
        DIRECTORY = System.getProperty("user.dir");
        folder_name = new File(DIRECTORY+"/files");
        if (!folder_name.exists())
            folder_name.mkdirs();
        System.out.println("DIRECTORY : "+ folder_name.getAbsolutePath());
    }

    private static final String WELCOME_MESSAGE =
            "\n ********************** "+
                    "\n Designed & Developed By : "+
                    "\n ********************** "+
                    "\n  Welcome to LOCK IT! "+
                    "\n  By, Locker Pvt Ltd. "+
                    "\n Developer: Karthik Kanumuri \n";
                   
private static final String MAIN_MENU_MESSAGE =
            "\n MAIN MENU - Select any of the following To Use LockedMe: \n"+
                    "1. List Current Files \n"+
                    "2. Business Operations \n"+
                    "3. Close Application";

    private static final String SECONDARY_MENU_MESSAGE =
            "   \nSelect any of the following Option: \n"+
                    "   1. Add a File \n"+
                    "   2. Search for a File\n"+
                    "   3. Delete a File \n"+
                    "   4. Go Back to Main Menu";
    void showPrimaryMenu() {
        System.out.println(MAIN_MENU_MESSAGE);
        try{
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option){
                case 1 : {
                    showFiles();
                    showPrimaryMenu();
                }
                case 2 : {
                    showSecondaryMenu();
                }
                case 3 : {
                    System.out.println("Thank You For Using LockedMe");
                    System.exit(0);
                }
                default: showPrimaryMenu();
            }
        }
        catch (Exception e){
            System.out.println("Please Enter Option 1, 2 or 3");
            showPrimaryMenu();
        }
    }

    void showSecondaryMenu() {
        System.out.println(SECONDARY_MENU_MESSAGE);
        try{
            Scanner scanner = new Scanner(System.in);
            char[] input = scanner.nextLine().toLowerCase().trim().toCharArray();
            char option = input[0];

            switch (option){
                case '1' : {
                    System.out.print(" For Adding a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim().toLowerCase();
                    addFile(filename);
                    break;
                }
                case '2' : {
                    System.out.print("For Searching a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    searchFile(filename);
                    break;
                }

                case '3' : {
                    System.out.print("For Deleting a file...Please Enter a File Name : ");
                    String filename = scanner.next().trim();
                    deleteFile(filename);
                    break;
                }
                case '4' : {
                    System.out.println("Going Back to MAIN menu");
                    showPrimaryMenu();
                    break;
                }
                default : System.out.println("Please enter Option 1, 2, 3 or 4");
            }
            showSecondaryMenu();
        }
        catch (Exception e){
            System.out.println("Please enter Option 1, 2, 3 or 4");
            showSecondaryMenu();
        }
    }

    void showFiles() {
        if (folder_name.list().length==0)
            System.out.println("The folder is empty");
        else {
            String[] list = folder_name.list();
            System.out.println("The files in "+ folder_name +" are :");
            Arrays.sort(list);
            for (String str:list) {
                System.out.println(str);
            }
        }
    }

    void addFile(String filename) throws IOException {
        File filepath = new File(folder_name +"/"+filename);
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equalsIgnoreCase(file)) {
                System.out.println("File " + filename + " is already exists at " + folder_name);
                return;
            }
        }
        filepath.createNewFile();
        System.out.println("File "+filename+"is added to "+ folder_name+" Successfully......");
    }

    void deleteFile(String filename) {
        File filepath = new File(folder_name +"/"+filename);
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equals(file) && filepath.delete()) {
                System.out.println("File " + filename + " deleted from " + folder_name);
                return;
            }
        }
        System.out.println("Delete Operation failed. FILE NOT FOUND");
    }

    void searchFile(String filename) {
        String[] list = folder_name.list();
        for (String file: list) {
            if (filename.equals(file)) {
                System.out.println("FOUND : File " + filename + " Exists at " + folder_name);
                return;
            }
        }
        System.out.println("File NOT found (FNF)");
    }

    public static void main(String[] args) {
        System.out.println(WELCOME_MESSAGE);
        LockerPvtLtd menu = new LockerPvtLtd();
        menu.showPrimaryMenu();
    }

}