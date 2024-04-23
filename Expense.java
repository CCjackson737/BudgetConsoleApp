public class Expense extends  Item 
{
    
    //Constructor
    public Expense(String _expenseName,double _expenseCost)
    {
        
        super(_expenseName,_expenseCost);

    }

    public String toCSV()
    {
        return "expense," + this.getName() + "," + this.getCost()+"\n";
    }

    public String toString()
    {
        return "Expense Name: " + this.getName() + " Expense Cost: " + this.getCost()+"\n";
    }

}
