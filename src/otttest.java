import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class otttest {
    String[] planNames=new String[]{"prime","premium","platinum"};
    Subscription[] planObjects=new Subscription[]{Factory.getPlan("prime"),Factory.getPlan("premium"),Factory.getPlan("platinum")};
    @Test 
    public void testObjectCreation()
    {
        String names[]=new String[]{"prime","platinum","premium","gold","silver","Prime","Plat","prEm"};
        Subscription exepectedObject[]=new Subscription[]{planObjects[0],planObjects[2],planObjects[1],null,null,planObjects[0],null,null};
        for(int i=0;i<names.length;i++)
        {
            assertEquals(exepectedObject[i], Factory.getPlan(names[i]));
        }
    }
    @Test 
    public void testPlanName()
    {
        String names[]=new String[]{"PRIME","PREMIUM","PLATINUM"};
        for(int i=0;i<names.length;i++){
            assertEquals(names[i], planObjects[i].plan.name());
        }
    }
    @Test
    public void testResolution(){
        String resolution[]=new String[]{"720p","1080p","4k"};
        for(int i=0;i<planObjects.length;i++)
        {
            assertEquals(resolution[i], planObjects[i].plan.resolution);

        }
    }
    @Test
    public void testConcurrentViewers()
    {
        int concurrentViewers[]=new int[]{3,5,10};
        for(int i=0;i<planObjects.length;i++)
        {
            assertEquals(concurrentViewers[i], planObjects[i].maximumConcurrentViewers);
        }
    }
    @Test
    public void testbaseFee(){
        double baseFee[]=new double[]{999,1299,1499};
        for(int i=0;i<planObjects.length;i++)
        {
            assertEquals(Double.doubleToLongBits(baseFee[i]), Double.doubleToLongBits(planObjects[i].baseFee));
        }

    }
    @Test
    public void testTaxSlab()
    {
        double taxSlab[]=new double[]{0.0875,0.1425,0.175};
        for(int i=0; i<taxSlab.length;i++)
        {
            assertEquals(Double.doubleToLongBits(taxSlab[i]),Double.doubleToLongBits(planObjects[i].taxslab));
        }
    }
    @Test 
    public void testTotalAmountPayable(){
        double expected[]=new double[]{1086.4125,1484.1075,1761.325};
        Subscription input[]=new Subscription[]{Factory.getPlan("prime"),Factory.getPlan("premium"),Factory.getPlan("platinum")};
        for(int i=0;i<input.length;i++)
        {
            assertEquals(Double.doubleToLongBits(expected[i]),Double.doubleToLongBits(input[i].getTotalAmount()));
        }
    }
    @Test
    public void testHasDiscount(){
        ottplan ob=new ottplan();
        String names[]=new String[]{"sayan","sayin","rrahul","aaaaaaaaa","aeiou","asayin","Saaaaaaaaaaaaaaaaaa"};
        Boolean expected[]=new Boolean[]{false,true,false,false,false,false,false};
        for (int i=0; i<names.length;i++) {
            assertEquals(expected[i], ob.hasDiscount(names[i]));
        }
    }
    @Test
    public void testHasConsecutiveCharacters(){
        ottplan ob=new ottplan();
        String input[]=new String[]{"sayan","aaaaaa","abababa","aabbbbbb","abcdd"};
        Boolean expected[]=new Boolean[]{false,true,false,true,true};
        for (int i=0;i<input.length;i++) {
            assertEquals(expected[i], ob.hasConsecutiveCharacters(input[i]));
        }
    }
    @Test
    public void testisVowel(){
        ottplan ob=new ottplan();
        char[] ip=new char[]{'a','A','e','B','c','z','O','u','U','o','i','J','I','U','u'};
        boolean[] expected=new boolean[]{true,true,true,false,false,false,true,true,true,true,true,false,true,true,true};
        for(int i=0;i<expected.length;i++)
        {
            assertEquals(expected[i], ob.isVowel(ip[i]));
        }
    }
    @Test
    public void testHasTwoDistinctVowels(){
        ottplan ob=new ottplan();
        String ip[]=new String[]{"aaaaa","aeaeaeae","aeiiou","kljhg","AeI","AAAAA","Sayn","nnnnn"};
        boolean op[]=new boolean[]{false,true,true,false,true,false,false,false};
        for(int i=0;i<ip.length;i++){
            assertEquals(op[i], ob.hasTwoDistinctVowels(ip[i]));
        }
    }
    @Test
    public void testGetNewAmount(){
        ottplan ob=new ottplan();
        Subscription input[]=new Subscription[]{Factory.getPlan("prime"),Factory.getPlan("premium"),Factory.getPlan("platinum")};
        double expected[]=new double[]{986.4125,1384.1075,1661.325};
        for(int i=0;i<input.length;i++)
        {
            assertEquals(Double.doubleToLongBits(expected[i]),Double.doubleToLongBits(ob.getNewAmount(input[i])),0.001);
        }
    }
}
