import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class init {
	static Scanner sos = new Scanner(System.in);
	static String input()
	{
		String inputs = sos.nextLine();
		//inputs = inputs.replaceAll("\\s","");
		return inputs;
	}
	static int match(String input1)///////////////////////need_modify
	{
		//String s1 = "IF//[(.*)//]-((renamed)|(modified)|(path-changed)|(size-changed))-THEN-((record-summary)|(record-detail)|(recover))";
		String s1 = "IF\\*[^\\*]*\\*((renamed)|(modified)|(path-changed)|(size-changed))\\*THEN\\*((record-summary)|(record-detail)|(recover))";
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
	
	public static void main(String[] args) //throws FileNotFoundException, IOException
	{
		try{
		String ss;
		int sum = 0;
		int i;
		String hel[];
		String hel1[];
	//	Tree trytree = new Tree("C://DEV-CPP");
	//	trytree.build_tree("C://QMDOWNLOAD",-1);
		work_public wc = new work_public();
		//solve trysolve = new solve(false,false,false,false,1,"C://tryoo",wc);
		//solve trysolve1 = new solve(false,false,false,false,0,"C://tryoo/out.txt",wc);
		solve [] soso = new solve[30];
		int summ = 0;
		//trysolve1.start();
		//trysolve.start();
		
		while(true)
		{
			ss = input();
			if(ss.equals("end"))break;
			hel= ss.split(";");
			for(i = 0;i < hel.length;i++)
			if((match(hel[i]) != 1))//
			  {
				System.out.println("INVALID(synax): "+ hel[i]);
			  }
			else
			{
				hel1 = hel[i].split("[\\*]");
				//System.out.println(hel1[1]);
				try{
					File file = new File(hel1[1]);
					if(!file.exists())
						System.out.println("INVALID(no_such_file): "+ hel1[1]);
					else if(((!file.isDirectory()) && hel1[4].equals("recover"))||( hel1[2].equals("modify")&& hel1[4].equals("recover"))||(hel1[2].equals("size-changed") && hel1[4].equals("recover")))
					{
						System.out.println("INVALID(about recover): "+ hel1[1]);
					}
					else
					{
						boolean tagtmp = false;
						int i1;
						for(i1 = 0;i1 < sum;i1 ++)
						{
							//System.out.println(i1);
							if(soso[i1].str.equals(hel1[1]))
							{
								tagtmp = true;
								break;
							}
						}//System.out.println(tagtmp);
							if(!tagtmp)
							{
							if(sum >= 8){System.out.println("OVER LIMIT!"); break;}
							boolean tag1,tag2,tag3,tag4;
							tag1 = hel1[2].equals("renamed")?true:false;
							tag2 = hel1[2].equals("modified")?true:false;
							tag3 = hel1[2].equals("path-changed")?true:false;
							tag4 = hel1[2].equals("size-changed")?true:false;
							int type = file.isDirectory()?1:0;
							soso[sum]= new solve(tag1,tag2,tag3,tag4,type,hel1[1],wc);
							sum ++;
							
							}
							
								if(hel1[2].equals("renamed"))
								{
									soso[i1].type1 = true;
									if(hel1[4].equals("record-summary"))
									{
										soso[i1].solve_type[1][2] = true;
									}
									if(hel1[4].equals("record-detail"))
									{
										soso[i1].solve_type[1][1] = true;
									}
									if(hel1[4].equals("recover"))
									{
										soso[i1].solve_type[1][3] = true;
									}
									
								}
								if(hel1[2].equals("modified"))
								{
									soso[i1].type2 = true;
									if(hel1[4].equals("record-summary"))
									{
										soso[i1].solve_type[2][2] = true;
									}
									if(hel1[4].equals("record-detail"))
									{
										soso[i1].solve_type[2][1] = true;
									}
									if(hel1[4].equals("recover"))
									{
										soso[i1].solve_type[2][3] = true;
									//	System.out.println("INVALID");
									}
								}
								if(hel1[2].equals("path-changed"))
								{
									soso[i1].type3 = true;
									if(hel1[4].equals("record-summary"))
									{
										soso[i1].solve_type[3][2] = true;
									}
									if(hel1[4].equals("record-detail"))
									{
										soso[i1].solve_type[3][1] = true;
									}
									if(hel1[4].equals("recover"))
									{
										soso[i1].solve_type[3][3] = true;
									}
								}
								if(hel1[2].equals("size-changed"))
								{
									soso[i1].type4 = true;
									if(hel1[4].equals("record-summary"))
									{
										soso[i1].solve_type[4][2] = true;
									}
									if(hel1[4].equals("record-detail"))
									{
										soso[i1].solve_type[4][1] = true;
									}
									if(hel1[4].equals("recover"))
									{
										soso[i1].solve_type[4][3] = true;
										//System.out.println("INVALID");
									}
								}
						}
					}
				catch (Exception e){}
			}
			
		}
		//run all threads
		if(sum < 5){System.out.println("Warning: less than 5 files!");}
		for(int iii = 0;iii < sum;iii ++)
		   soso[iii].start();
		test tt = new test(wc);
		tt.start();
		}catch(Exception e){}
	}

}
