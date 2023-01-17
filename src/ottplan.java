import java.text.DecimalFormat;
import java.util.*;
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
public class ottplan {
    boolean notConsecutive(String name)
    {
        for(int i=1;i<name.length();i++)
        {
            if(name.charAt(i)==name.charAt(i-1))
                return false;
        }
        return true;
    }
    boolean distinctVowels(String name)
    {
        int[] hash =new int[5];
        Arrays.fill(hash,0);
        for(int i=0;i<name.length();i++)
        {
            char ch=name.charAt(i);
            if(ch=='a' || ch=='A')
                hash[0]++;
            if(ch=='e' || ch=='E')
                hash[1]++;
            if(ch=='i' || ch=='I')
                hash[2]++;
            if(ch=='o' || ch=='O')
                hash[3]++;
            if(ch=='u' || ch=='U')
                hash[4]++;
        }
        int distinct=0;
        for (int i=0;i<5;i++)
        {
            if(hash[i]>=1)
                distinct++;
        }
        return distinct>=2?true:false;
    }
    boolean isConsonant(char ch)
    {
        if(ch=='a'||ch=='A'||ch=='e'||ch=='E'||ch=='i'||ch=='I'||ch=='o'||ch=='O'||ch=='u'||ch=='U')
            return false;
        return true;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        ottplan ob=new ottplan();
        System.out.println("Enter Your Name");
        String name=sc.nextLine();
        System.out.println("Enter Your Plan");
        String plan=sc.nextLine();
        Boolean isDiscount=false;
        if(ob.isConsonant(name.charAt(0)) && ob.distinctVowels(name) && ob.notConsecutive(name) && name.length()<=10)
            isDiscount=true;
        try{
            Subscription obj=Factory.getPlan(plan);
            obj.calculate(isDiscount);
            obj.display();
        }
        catch(NullPointerException e) {
            System.out.println("Invalid Plan Entered");
        }
    }
}