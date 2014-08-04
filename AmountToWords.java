import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.*;


public class AmountToWords {
	
	public static Map <Integer , String> denominations;
	public static String [] tens;
	public static String [] upto19;
	public static String rupeeInWords = new String();
	
	public AmountToWords(){
		
		denominations= new LinkedHashMap <Integer, String>();
		
		denominations.put(10000000, "crore ");
		denominations.put(100000, "lakh ");
		denominations.put(1000, "thousand ");
		denominations.put(100, "hundred ");
		denominations.put(1, "");
		
		upto19 = new String[] {"", "one ", "two ", "three ", "four ", "five ", "six ", "seven ", "eight ", "nine ", "ten ",
				"eleven ", "twelve ", "thirteen ", "fourteen ", "fifteen ", "sixteen ", "seventeen ", "eighteen ", "ninteen "};
		tens = new String[] {"", "twenty ", "thirty ", "forty ", "fifty ", "sixty ", "seventy ", "eighty ", "ninty "};
	}	

	public static void main(String args[])
	{
		String amount = "4000040.78.99";
		AmountToWords amt = new AmountToWords();
		String inWords = "";
		if(amount.contains("."))
		{
			String[] amounts = amount.split("\\.");
			
			if(amounts.length > 2 )
			{
				System.out.println("Incorrect Input");
			}
			else

			{
				if(amounts[0].equals("0") || amounts[0].equals(""))
				{
					inWords += getPaiseValue(amounts[1]) + "paise only";
				}
				else
				{
					inWords = getRupeeValue(amounts[0]) + "rupees and ";
					inWords += getPaiseValue(amounts[1]) + "paise only";		
				}				
			}			
		}
		else
		{
			inWords = getRupeeValue(amount) + "rupees only";
		}
		System.out.println(inWords);
	}
	
	public static String splitInto2Digits(int amount)
	{        
        if(amount < 19)
        {
            return upto19[amount];
        }
        else
        {            
            int ten = amount % 100;
            return tens[(ten / 10) - 1] + upto19[ten % 10];
        }
    }
	
	public static String getRupeeValue(String Rupees)
	{
		Integer number = Integer.parseInt(Rupees);
        
    	Set<Integer> s = denominations.keySet();
    	Iterator<Integer> iter = s.iterator();
    	
    	while(iter.hasNext())
    	{
    		Integer i = iter.next();
    		
    		Integer digit = number / i; 
			if(digit > 0 )
			{
				rupeeInWords += splitInto2Digits(digit) + denominations.get(i);  
			}
			
			if(i == 100 && number % 100 != 0 && !rupeeInWords.equals(""))
				rupeeInWords += "and ";
			number = number % i;   
    	}
    	return rupeeInWords; 
	}
	
	public static String getPaiseValue(String paiseValue)
	{
		if(paiseValue.length() == 1)
		{
			paiseValue += "0";
		}		
		String paiseInWords = splitInto2Digits(Integer.parseInt(paiseValue));
		return paiseInWords;
	}
	
	public static String getRupee(String rupeeValue)
	{
		int number = Integer.parseInt(rupeeValue);
        
    	Set<Integer> s = denominations.keySet();
    	Iterator<Integer> iter = s.iterator();
    	
    	while(iter.hasNext())
    	{
    		int i = iter.next();
        	Integer j = number / i;
        	
            rupeeInWords += splitInto2Digits(j);
            
            if( j!= 0 )
            {
            	rupeeInWords += denominations.get(i) ;     	
            	number = number % i;	            	
        	}	            
    	}
    return rupeeInWords;		
	}
	
}