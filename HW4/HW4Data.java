/*
 * Name: Xudong Song
 * Andrew ID: xudongs
 * Course#: 08-600
 * Homework #3
 * Date 17/SEPT/2014
 */

import java.util.Date;

public class HW4Data {
	
	private Date transDate;
	private String transDesc;
	private double transAmount;
	private boolean isDeposit;
	private int checkNumber;
	private double transFee;
	
	static int num = 0;
	static int checkNum = 100;
	
	public HW4Data(Date d, String desc, double amount){
		transDate = d;
		transDesc = desc;
		transAmount = amount;
		HW4Data.num++;
		
		if(amount>0){
			isDeposit = true;
			if(amount>=1 && amount<100)
				transFee = 0.25;
			else
				transFee = amount*0.0025;
		}
		else{
			isDeposit = false;
			checkNumber = ++HW4Data.checkNum;
			if((-1)*amount<10)
				transFee = 0.10;
			else if((-1)*amount>=10 && (-1)*amount<=100)
				transFee = amount*(-0.01);
			else
				transFee = 1;
		}
		
	}
	
	public Date getDate(){
		return transDate;
	}
	
	public String getDescription(){
		return transDesc;
	}
	
	public double getAmount(){
		return transAmount;
	}
	
	public boolean isDeposit(){
		return isDeposit;
	}
	
	public double getFee(){
		return transFee;
	}
	
	public int getCheckNumber(){
		return checkNumber;
	}
	
	public static void sort(HW4Data[] transactions){
		
		for(int i=0; i<HW4Data.num; i++){
			for (int j=i+1; j<HW4Data.num; j++) {
                if (transactions[i].getDate().compareTo(transactions[j].getDate()) > 0) {
                	HW4Data temp = transactions[i];
                	transactions[i] = transactions[j];
                	transactions[j] = temp;
                }
            }
		}
		
		
	}

}
