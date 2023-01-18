import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
enum Plan{
    PRIME("720p"),
    PREMIUM("1080p"),
    PLATINUM("4k");
    public String resolution;
    Plan(String resolution)
    {
        this.resolution=resolution;
    }
}
class Subscription{
    protected String resolution;
    protected String planName;
    protected int maximumConcurrentViewers;
    protected double baseFee;
    protected double taxslab;
    protected double totalAmountPayable;
    @Override
    public String toString(){
      return String.format("Plane Name:%s%nNo. Of Concurrent Viewers:%d%nSupported Resolution:%s%nTotal Amount Payable:%.2f",planName,maximumConcurrentViewers,resolution,totalAmountPayable);  
    } 
    public void setTotalAmount()
    {
        this.totalAmountPayable=this.baseFee+this.baseFee*this.taxslab;
    }
    public double getTotalAmount()
    {
        return this.totalAmountPayable;
    }

}
class Prime extends Subscription{
    
    Prime(int maximumConcurrentViewers, double baseFee, double taxslab)
    {
        this.planName=Plan.PRIME.name();
        this.maximumConcurrentViewers=maximumConcurrentViewers;
        this.resolution=Plan.PRIME.resolution;
        this.baseFee=baseFee;
        this.taxslab=taxslab;
        setTotalAmount();
    }
}
class Premium extends Subscription{
    Premium(int maximumConcurrentViewers, double baseFee, double taxslab)
    {
        this.planName=Plan.PREMIUM.name();
        this.maximumConcurrentViewers=maximumConcurrentViewers;
        this.resolution=Plan.PREMIUM.resolution;
        this.baseFee=baseFee;
        this.taxslab=taxslab;
        setTotalAmount();
    }
}
class Platinum extends Subscription{
    Platinum(int maximumConcurrentViewers, double baseFee, double taxslab)
    {
        this.planName=Plan.PLATINUM.name();
        this.maximumConcurrentViewers=maximumConcurrentViewers;
        this.resolution=Plan.PLATINUM.resolution;
        this.baseFee=baseFee;
        this.taxslab=taxslab;
        setTotalAmount();
    }
}
class Factory{
    static Map<String,Subscription> planMap=new HashMap<>();
    static {
        planMap.put(Plan.PRIME.name(),new Prime(3,999,0.0875));
        planMap.put(Plan.PREMIUM.name(),new Premium(5,1299,0.1425));
        planMap.put(Plan.PREMIUM.name(),new Platinum(10,1499,0.175));
    }
    public static Subscription getPlan(String operator)
    {
        return planMap.get(operator.toUpperCase());
    }
}
class discountEligible{
   

}
public class ottplan {
    boolean hasConsecutiveCharacters(String name)
    {
        for(int i=1;i<name.length();i++)
        {
            if(name.charAt(i)==name.charAt(i-1))
                return true;
        }
        return false;
    }
    boolean isVowel(char ch)
    {
        Set<Character> vowelSet=new HashSet<Character>();
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');
        return vowelSet.contains(ch); 
    }
    boolean hasTwoDistinctVowels(String name)
    {
        Map<Character,Integer> vowelMap=new HashMap<Character,Integer>();
        for(int i=0;i<name.length();i++)
        {
            char ch=name.charAt(i);
            if(isVowel(ch))
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
    boolean hasDiscount(String name)
    {
        name=name.toLowerCase();
        return (!isVowel(name.charAt(0)) && hasTwoDistinctVowels(name) && !hasConsecutiveCharacters(name) && name.length()<=10);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ottplan ob=new ottplan();
        String name="", plan="";
        while(name.length()==0)
        {
            System.out.println("Enter Your Name");
            name=sc.nextLine();
        }
        while(plan.length()==0)
        {
            System.out.println("Enter Your Plan");
            plan=sc.nextLine();  
        }    
        try{
            Subscription obj=Factory.getPlan(plan);
            System.out.println(obj.toString()); 
            if(ob.hasDiscount(name))
            {
                double newAmount=obj.getTotalAmount()-100;
                System.out.println("Eligible For Discount");
                System.out.printf("New Payable Amount:%.2f",newAmount);
            }
        }
        catch(NullPointerException e) {
            System.out.println("Invalid Plan Entered");
        }
        sc.close();
    }
}
