import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class budgetApp
{
public static void main(String[] args) throws FileNotFoundException
    {
        //Scanner Asked for this data if new user
        

        ArrayList<Item> listBudget = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Hello Welcome to Budget! Please Enter your name to get started: ");
        String name = input.nextLine();
        System.out.println("Hello " + name + ", please enter your monthly income amount: ");
        
        //Ask for income with error handling to make sure that
        double income = 0;
        boolean success = false;
        while(!success){
        try {
            income = Double.parseDouble(input.nextLine());
            success = true;
        }
        catch (Exception e){
            System.out.println("Please enter a valid Number:");
        }
        }
        
        if(readFromFile(name)!=null)
        {
        System.out.println("We're currently loading your data.");
        listBudget = readFromFile(name);
        }
        else
        {
            System.out.println("It seems that we don't have your data on file, you should add some information!");
        }
        

        //while + switch to let the user work for as long as they'd like
        int choice=99;

        while(choice!=0)
        {
        System.out.println("\n_________________________________________BUDGET_________________________________________\n");
        System.out.println("1. Visualize Data");
        System.out.println("2. Add Savings Item");
        System.out.println("3. Add Expense");
        System.out.println("4. Remove Savings Item");
        System.out.println("5. Remove Expense");
        System.out.println("6. Update monthly income");
        System.out.println("0. Exit");

        success = false;
        while(!success){
        try {
            choice = Integer.parseInt(input.nextLine());
            success = true;
        }
        catch (Exception e){
            System.out.println("Please enter a valid Number:");
        }
        }
            switch(choice)
            {
                case 1:
                    visualizeData(listBudget,income);
                    break;
                case 2:
                    String itemName = "";
                    double itemPrice = 0;
                    double itemPercent = 0;
                    System.out.println("To add savings item first enter the item name:");
                    itemName = input.nextLine();
                    System.out.println("Now enter the price in $");
                    
                    success = false;
                    while(!success){
                    try {
                        itemPrice = Double.parseDouble(input.nextLine());
                        success = true;
                         }
                    catch (Exception e){
                    System.out.println("Please enter a valid Number:");
                    }
                    }
                    System.out.println("Now enter the percent you would like to put towards it every month: ");
                    success = false;
                    while(!success){
                    try {
                        itemPercent = Double.parseDouble(input.nextLine());
                        success = true;
                         }
                    catch (Exception e){
                    System.out.println("Please enter a valid Number:");
                    }
                    }
                    Saving tempSavingItem = new Saving(itemName, itemPrice, itemPercent);
                    listBudget.add(tempSavingItem);
                    System.out.println("Item " + itemName + " added");

                    break;
                case 3:
                    String expenseName = "";
                    double expensePrice = 0;
                    System.out.println("To add an expense first enter the item name:");
                    expenseName = input.nextLine();
                    System.out.println("Now enter the price in $");
                    
                    success = false;
                    while(!success){
                    try {
                        expensePrice = Double.parseDouble(input.nextLine());
                        success = true;
                         }
                    catch (Exception e){
                    System.out.println("Please enter a valid Number:");
                    }
                    }
                    Expense tempExpenseItem = new Expense(expenseName,expensePrice);
                    listBudget.add(tempExpenseItem);
                    System.out.println("Item " + expenseName + " added");
                    break;
                case 4:
                    for(Item item:listBudget)
                    {
                        if(item instanceof Saving)
                        {
                            System.out.println(item.toString());
                        }   
                    }

                    System.out.println("Type the name of the item your would like to remove. Type x to return to menu.");
                    String option ="";
                    option=input.nextLine();
                    ArrayList<Item> tempList = new ArrayList<>();
                    if(option.equalsIgnoreCase("x"))
                    {
                        break;
                    }
                    else
                    {
                        for(Item item:listBudget)
                        {
                            if(!item.getName().equalsIgnoreCase(option))
                            {
                            
                            tempList.add(item);
                            }
                            else{
                                System.out.println("Now removing item " + item.getName());
                            }   
                        }
                        listBudget.clear();
                        listBudget.addAll(tempList);
                        System.out.println("Item removed successfully");
                    }
                    break;
                case 5:
                    for(Item item:listBudget)
                    {
                        if(item instanceof Expense)
                        {
                            System.out.println(item.toString());
                        }   
                    }

                    System.out.println("Type the name of the item your would like to remove. Type x to return to menu.");
                    option ="";
                    option=input.nextLine();
                    ArrayList<Item> tempExpenseList = new ArrayList<>();
                    if(option.equalsIgnoreCase("x"))
                    {
                        break;
                    }
                    else
                    {
                        for(Item item:listBudget)
                        {
                            if(!item.getName().equalsIgnoreCase(option))
                            {
                            
                            tempExpenseList.add(item);
                            }
                            else{
                                System.out.println("Now removing item " + item.getName());
                            }   
                        }
                        listBudget.clear();
                        listBudget.addAll(tempExpenseList);
                        System.out.println("Expense removed successfully");
                    }
                    break;
                case 6:
                    System.out.println("Your current monthly income is set to " + income);
                    
                    System.out.println("Please enter a new income: ");
                    success = false;

                    while(!success){
                    try {
                    income = Double.parseDouble(input.nextLine());
                    success = true;
                    }
                    catch (Exception e){
                    System.out.println("Please enter a valid Number:");
                    }
        }
                    break;
                case 0:
                    System.out.println("Goodbye");
                    String fileHeader = name + ", "+income + "\n";
                    writeToFile(listBudget, fileHeader, name);
                    break;
                default:
                    System.out.println("Sorry not a valid option. Please enter a valid choice.");
            }
        }


        /*
        String name = "Cameron";
        double monthlyIncome = 1200.00;
        String fileHeader = name + ", "+monthlyIncome + "\n";
        //Dummy Data
        Expense expense1 = new Expense("Light Bill",120.00);
        Expense expense2 = new Expense("Water Bill",40.00);
        Expense expense3 = new Expense("Internet Bill",40.00);
        Expense expense4 = new Expense("Gas",80.00);
        Saving saving1 = new Saving("Car",12600.00,5.00);
        Saving saving2 = new Saving("PS5",499.99,5.00);
        Saving saving3 = new Saving("Engagement Ring",8000.00,10.0);
        //Dummy Data added to the array list
        listBudget.add(expense1);
        listBudget.add(expense2);
        listBudget.add(expense3);
        listBudget.add(expense4);
        listBudget.add(saving2);
        listBudget.add(saving3);
        listBudget.add(saving1);
        //listBudget.remove(expense4);
        */
        //writes this data to file;

    }
    //Scanner ask for data and creates item
    public static void addExpense()
    {

    }
    //Scanner ask for data and creates item
    public static void addSaving()
    {

    }

    
    public static String writeToFile(ArrayList<Item> _budgetList,String _fileHeader,String _name) throws FileNotFoundException
    {
        //Creates File using the users Name
        File csvFile = new File(_name +"budget.csv");
        //Creates Printwriter
        PrintWriter out = new PrintWriter(csvFile);
        
        //Puts necessary data in the file
        out.print(_fileHeader);
        for(Item item : _budgetList)
        {
            out.print(item.toCSV());
        }
        //closes buffer
        out.close();
        //Alert to let the user knwow their data has been saved
        return "File Saved!";
        
    }

    public static ArrayList<Item> readFromFile(String _name)
    {
         Scanner fileScanner;
        
        ArrayList<Item> budgetList = new ArrayList<>();
        try{
            fileScanner = new Scanner(new File(_name+"budget.csv"));
        
        String line;
        String[] lineSplit;
        while(fileScanner.hasNext())
        {
            line = fileScanner.nextLine();
            lineSplit = line.split(",");
            if(lineSplit[0].equalsIgnoreCase("expense"))
            {
                Expense temp = new Expense(lineSplit[1],Double.parseDouble(lineSplit[2]));
                budgetList.add(temp);
            }
            else if(lineSplit[0].equalsIgnoreCase("saving"))
            {
                Saving temp = new Saving(lineSplit[1],Double.parseDouble(lineSplit[2]),Double.parseDouble(lineSplit[3]));
                budgetList.add(temp);
            }
            else
            {

            }
        }
        
        for(Item item : budgetList)
        {
            System.out.print(item.toCSV());
        }
        
        
        fileScanner.close();
        
        }
        catch(FileNotFoundException ex)
        {
            return null;  
        }
        return budgetList;
    }

    public static void visualizeData(ArrayList<Item> _budgetList,double _income)
    {
        double totalExpenses = 0;
        double totalSavings = 0;
       System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for(Item item: _budgetList)
        {
            if(item instanceof Expense)
            {
                totalExpenses+=item.getCost();
                System.out.println(item.toString());
            }
            else if(item instanceof Saving)
            {
                double percentTopercent = ((Saving) item).getPPPP()/100;
                totalSavings+=(_income*percentTopercent);
                System.out.println(item.toString());
            }
            else
            {

            }
            
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        double disposableIncome=_income-totalExpenses;
        System.out.println("Your total income each month is "+ _income);
        System.out.println("Your total expenses each month is " + totalExpenses);
        System.out.println("Your total you can save each month is " + disposableIncome);
        System.out.println("Your total you should be saving to reach your goals each month is " + totalSavings);
        System.out.println("This leaves "+(disposableIncome-totalSavings)+" each month to whatever you want with");
        
        System.out.println("Using the percent values you gave for each item you are saving for ");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for(Item item: _budgetList)
        {
            if(item instanceof Saving)
            {
                double number = item.getCost()/((disposableIncome*((Saving) item).getPPPP())/100);
                String roundedNumber = decimalFormat.format(number);
                System.out.println("It will take around "+ roundedNumber + " months to save " + item.getCost()+" for " + item.getName() + " while saving " + ((Saving) item).getPPPP() + "% of your free income.");
            }
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        System.out.println("Press x to return to main menu");
         Scanner input = new Scanner(System.in);
         String choice = "o";
         while(!choice.equalsIgnoreCase("x"))
         {
            choice=input.nextLine();
         }

    }

}


 