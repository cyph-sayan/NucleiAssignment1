import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;;
abstract class Subscription{
    protected String resolution;
    protected String planName;
    protected int noOfViewers;
    protected double amount;
    protected String dis;
    abstract void calculate(boolean a);
    void display()
    {
        DecimalFormat df=new DecimalFormat("##.00");
        System.out.println(dis);
        System.out.println("Plane Name:"+planName);
        System.out.println("No. Of Concurrent Viewers:"+noOfViewers);
        System.out.println("Supported Resolution:"+resolution);
        System.out.println("Total Amount:"+df.format(amount));
    }
    public double getTotalAmount()
    {
        return amount;
    }
}
class Prime extends Subscription{
    @Override
    public void calculate(boolean discount){
        this.planName="Prime";
        this.noOfViewers=3;
        this.resolution="720p";
        this.amount=999;
        this.amount+=(0.0875*this.amount);
        if(discount){
            this.dis="Eligible for Discount";
            this.amount-=100;
        }
        else
            this.dis="Not Eligible for Discount";
    }
}
class Premium extends Subscription{
    @Override
    public void calculate(boolean discount) {
        this.planName="Premium";
        this.noOfViewers=5;
        this.resolution="1080p";
        this.amount=1299;
        this.amount+=(0.1425*this.amount);
        if(discount){
            this.dis="Eligible for Discount";
            this.amount-=100;
        }
        else
            this.dis="Not Eligible for Discount";
    }
}
class Platinum extends Subscription{
    @Override
    public void calculate(boolean discount) {
        this.planName="Platinum";
        this.noOfViewers=10;
        this.resolution="4k";
        this.amount=1499;
        this.amount+=(0.175*this.amount);
        if(discount){
            this.dis="Eligible for Discount";
            this.amount-=100;
        }
        else
            this.dis="Not Eligible for Discount";
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
        Boolean isDiscount=false;
        if(ob.hasDiscount(name))
            isDiscount=true;
        try{
            Subscription obj=Factory.getPlan(plan);
            obj.calculate(isDiscount);
            obj.display();
            System.out.println("Amount Before Discount:"+obj.getTotalAmount());
        }
        catch(NullPointerException e) {
            System.out.println("Invalid Plan Entered");
        }
        sc.close();
    }
}