public class Saving extends Item
{
    
    double percentPerPayPeriod;
    //Constructor
    public Saving(String _savingName,double _savingCost,double _percentPerPayPeriod)
    {
        super(_savingName,_savingCost);
        percentPerPayPeriod = _percentPerPayPeriod;
    }
    //getter

    public String getName()
    {
        return Name;
    }

    public double getPPPP()
    {
        return percentPerPayPeriod;
    }

    //setter
    public void setPPPP(double _percentPerPayPeriod)
    {
        percentPerPayPeriod = _percentPerPayPeriod;
    }
    public String toCSV()
    {
        return "saving," + this.getName() + "," + this.getCost() + "," + percentPerPayPeriod +"\n";
    }
    public String toString()
    {
        return "Savings item name: " + this.getName() + " cost: " + this.getCost() + " percent paying per month: " + percentPerPayPeriod +"%\n";
    }
}
