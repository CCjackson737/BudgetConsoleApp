public class Item 
{
    String Name;
    double Cost;
    
    //Constructor
    public Item(String _Name,double _Cost)
    {
        
        Name = _Name;
        Cost = _Cost;

    }
    //Getters
    public String getName()
    {
        return Name;
    }

    public double getCost()
    {
        return Cost;
    }
    //Setters
    public void setName(String _Name)
    {
        Name = _Name;
    }

    public void setCost(double _Cost)
    {
        Cost = _Cost;
    }

    public String toCSV()
    {
        return "item," + this.getName() + "," + this.getCost()+"\n";
    }
}
