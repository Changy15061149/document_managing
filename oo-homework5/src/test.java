import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class test extends Thread{
	work_public wc;
	static Scanner sos = new Scanner(System.in);
	public test(work_public wc1)
	{
		wc = wc1;
	}
	String inpt()
	{
		String inputs = sos.nextLine();
		return inputs;
	}
	int  match(String input1)
	{
		String s1 = "(C|M|D|A)\\*[^\\*]*\\*[^\\*]*";
		Pattern p1,p2;
		p1 = Pattern.compile(s1);
		Matcher m1,m2;
		m1 = p1.matcher(input1);
		boolean ans1,ans2,ans;
		ans1 = m1.find();
		//System.out.println(ans1+" "+input1);
		ans = ans1;
		if(ans1)
		{
			if(m1.start() != 0 || m1.end() != input1.length())
				return -1;
		}
		if(ans)
		   return 1;
		else return -1;
	}
	public void run()
	{
		try{
		System.out.println("Now start test!");
		while(true)
		{
			String str = inpt();
			if(match(str)!= 1)
			{
				System.out.println("Test: Invalid Synax !");
			}
			else
			{
				wc.solvetest(str);
			}
		}
		}catch (Exception e){}
	}

}
