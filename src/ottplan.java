import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
abstract class Subscription{
    public enum namesOfPlans{
        prime("PRIME"), premium("PREMIUM"), platinum("PLATINUM");
        private final String stringvalue;
        namesOfPlans(final String s)
        {
            stringvalue=s;
        }
        public String getValue()
        {
            return stringvalue;
        }  
    }
    public enum supportedResolution{
        prime("720p"), premium("1080p"), platinum("4k");
        private final String stringvalue;
        supportedResolution(final String s)
        {
            stringvalue=s;
        }
        public String getValue()
        {
            return stringvalue;
        }  
    }
    public enum maxConView{
        prime(3), premium(5), platinum(8);
        private final int value;
        maxConView(final int n)
        {
            value=n;
        }
        public int getValue()
        {
            return value;
        }  
    }
    public enum priceslab{
        prime(999), premium(1299), platinum(1499);
        private final int value;
        priceslab(final int n)
        {
            value=n;
        }
        public int getValue()
        {
            return value;
        }  
    }
    public enum taxslab{
        prime(0.0875), premium(0.1425), platinum(0.175);
        private final double value;
        taxslab(final Double n)
        {
            value=n;
        }
        public double getValue()
        {
            return value;
        }  
    }
    protected String resolution;
    protected String planName;
    protected int maximumConcurrentViewers;
    protected double subscriptionFee;
    protected String discountEligibilty;
    abstract void calculate();
    @Override
    public String toString(){
      return String.format("%s%nPlane Name:%s%nNo. Of Concurrent Viewers:%d%nSupported Resolution:%s%nTotal Amount Payable:%.2f",discountEligibilty,planName,maximumConcurrentViewers,resolution,subscriptionFee);  
    } 
    double getTotalAmount()
    {
        return subscriptionFee;
    }
    void setTotalAmount(double newAmount)
    {
        this.subscriptionFee=newAmount;
    }
    void setEligibleForDiscount(String eligible)
    {
        this.discountEligibilty=eligible;
    }
}
class Prime extends Subscription{
    @Override
    public void calculate(){
        this.planName=namesOfPlans.prime.getValue();
        this.maximumConcurrentViewers=maxConView.prime.getValue();
        this.resolution=supportedResolution.prime.getValue();
        this.subscriptionFee=priceslab.prime.getValue();
        this.subscriptionFee+=(taxslab.prime.getValue()*this.subscriptionFee);
    }
}
class Premium extends Subscription{
    @Override
    public void calculate() {
        this.planName=namesOfPlans.premium.toString();
        this.maximumConcurrentViewers=maxConView.premium.getValue();
        this.resolution=supportedResolution.premium.toString();
        this.subscriptionFee=priceslab.premium.getValue();
        this.subscriptionFee+=(taxslab.premium.getValue()*this.subscriptionFee);
    }
}
class Platinum extends Subscription{
    @Override
    public void calculate() {
        this.planName=namesOfPlans.platinum.getValue();
        this.maximumConcurrentViewers=maxConView.platinum.getValue();
        this.resolution=supportedResolution.platinum.getValue();
        this.subscriptionFee=priceslab.platinum.getValue();
        this.subscriptionFee+=(taxslab.platinum.getValue()*this.subscriptionFee);
    }
}
class Factory{
    static Map<String,Subscription> planMap=new HashMap<>();
    static {
        planMap.put("prime",new Prime());
        planMap.put("premium",new Premium());
        planMap.put("platinum",new Platinum());
    }
    public static Subscription getPlan(String operator)
    {
        return planMap.get(operator.toLowerCase());
    }
}
class discountEligible{
    boolean hasConsecutiveCharacters(String name)
    {
        for(int i=1;i<name.length();i++)
        {
            if(name.charAt(i)==name.charAt(i-1))
                return true;
        }
        return false;
    }
    boolean hasTwoDistinctVowels(String name)
    {
        Map<Character,Integer> vowelMap=new HashMap<Character,Integer>();
        for(int i=0;i<name.length();i++)
        {
            char ch=name.charAt(i);
            if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
            {
                if(vowelMap.containsKey(ch)){
                    vowelMap.put(ch,vowelMap.get(ch)+1);
                }
                else{
                    vowelMap.put(ch,1);
                }
            }
        }
        if(vowelMap.size()>=2)
        {
            return true;
        }
        return false;
    }
    boolean isConsonant(char ch)
    {
        if(ch=='a'|| ch=='e'||ch=='i'||ch=='o'||ch=='u')
            return false;
        return true;
    }

}
public class ottplan {
    boolean hasDiscount(String name)
    {
        name=name.toLowerCase();
        discountEligible dis=new discountEligible();
        return (dis.isConsonant(name.charAt(0)) && dis.hasTwoDistinctVowels(name) && !dis.hasConsecutiveCharacters(name) && name.length()<=10);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ottplan ob=new ottplan();
        System.out.println("Enter Your Name");
        String name=sc.nextLine();
        System.out.println("Enter Your Plan");
        String plan=sc.nextLine();      
        try{
            Subscription obj=Factory.getPlan(plan);
            obj.calculate();
            if(ob.hasDiscount(name))
            {
                obj.setTotalAmount(obj.getTotalAmount()-100);
                obj.setEligibleForDiscount("Eligible For Discount");
            }
            else
            {
                obj.setEligibleForDiscount("Not Eligible For Discount");
            }
            System.out.println(obj.toString()); 
        }
        catch(NullPointerException e) {
            System.out.println("Invalid Plan Entered");
        }
        sc.close();
    }
}