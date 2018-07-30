
public class solve  extends Thread{
	
	
	int type;
	boolean type1,type2,type3,type4;
	int s1,s2,s3,s4;
	int sum;
	boolean [][] solve_type = new boolean[10][10]; 
	int [][] ans = new int [10][10];
	String str;
	String strstr;
	Tree tr ;
	work_public wc;
	
	String find_father(String nowpos)
	{
		
	    String[] hel = nowpos.split("\\/");
		String st1 = "";
		for(int i = 0;i < hel.length-1;i ++)
		{
			st1 = st1 + hel[i];
			if(i != hel.length - 2)
				st1 = st1 + "/";
		}
		   
		return st1;
	}
	
	public solve(boolean t1,boolean t2,boolean t3,boolean t4,int t,String input,work_public wc1)
	{
		type1 = true;
		type2 = true;
		type3 = true;
		type4 = true;
		type = t;
		str = input;
		sum = 0;
		tr = new Tree(input);
		tr.build_tree(input, -1);
		wc = wc1;
		if(type == 0)
			strstr = find_father(str);
		else
			strstr = str;
	}
	void same_pace(Tree t1,Tree t2)
	{
		int i = t2.queue.size();
		i--;
		for(i = i;i >= 0;i--)
		{
			t2.queue.remove(i);
		}
		for(i = 0;i < t1.queue.size();i++)
		{
			t2.queue.add(t1.queue.get(i));
		}
		t2.sum = t1.sum;
	}
	long find_resize_file(data_tree wantedfile,Tree t1)
	{
		boolean ans = false;
		int i = 0;
		//System.out.println(t1.sum);
		for(i = 0; i < t1.queue.size();i ++)
		   {
			// System.out.println(t1.queue.get(i).path);
			 if(t1.queue.get(i).path.equals(wantedfile.path))
			 	{
				// System.out.println("file_resize_place!:"+str+" "+t1.queue.get(i).size+"->"+wantedfile.size);
				 	if(t1.queue.get(i).size != wantedfile.size)
				 	{
				 		return t1.queue.get(i).size;
				 	}
				 	else return -1;
				 }
		   }
		return 0;
	}
	long find_modify_file(data_tree wantedfile,Tree t1)
	{
		int i = 0;
		for(i = 0; i < t1.queue.size();i ++)
		   {
			 if(t1.queue.get(i).path.equals(wantedfile.path))
			 	{
				 if(t1.queue.get(i).modify_time != wantedfile.modify_time)
				 	{
				 		return t1.queue.get(i).modify_time;
				 	}
				 else return -1;
				 }
		   }
		return 0;
	}
	String find_modify_name(data_tree wantedfile,Tree t1)
	{
		int i = 0;
		for(i = 0; i < t1.queue.size();i ++)
			 if(t1.queue.get(i).path.equals(wantedfile.path))
			 	return "none";
		for(i = 0; i < t1.queue.size();i++)
		{
			if(find_father(t1.queue.get(i).path).equals(find_father(wantedfile.path)))
			{
				if(t1.queue.get(i).modify_time == wantedfile.modify_time && t1.queue.get(i).size == wantedfile.size)
				{
					boolean tata = false;
					for(int j = 0; j < tr.queue.size(); j++)
					{
						if(tr.queue.get(i).path.equals(t1.queue.get(i).path)){tata = false;break;}
					}
					if(!tata)
					return t1.queue.get(i).path;
				}
			}
		}
		return "none";
	}
	String find_modify_path(data_tree wantedfile,Tree t1)
	{
		int i = 0;
		for(i = 0; i < t1.queue.size();i ++)
			 if(t1.queue.get(i).path.equals(wantedfile.path))
			 	return "none";
		for(i = 0; i < t1.queue.size();i++)
		{
				if(t1.queue.get(i).modify_time == wantedfile.modify_time && t1.queue.get(i).size == wantedfile.size && t1.queue.get(i).name.equals(wantedfile.name))
				{
					boolean tata = false;
					for(int j = 0; j < tr.queue.size(); j++)
					{
						if(tr.queue.get(i).path.equals(t1.queue.get(i).path)){tata = false;break;}
					}
					if(!tata)
					return t1.queue.get(i).path;
				}
		}
		return "none";
	}
	public void run()
	{
	  try{
		int pdtag = 0;
		
		/*while(pdtag == 0)
		{
			//modify pd tag
		}*/
		//tr = new Tree(str);
		//tr.build_tree(str, -1);
	//	System.out.println(str);
		while(true)
		{
			//check()
			int i = 0;
			if(tr.queue.size() == 0){System.out.println("File illegal modify!");break;}
			for(i = 0;i < tr.queue.size(); i++)
			{
				//System.out.println(tr.queue.get(i).path+"    "+str);
				if(tr.queue.get(i).path.equals(str))break;
			}
		//	System.out.println(str+ " " + wc.cnt);
			//wc.lock();
			Tree tree1 = new Tree(str);
			wc.solve(strstr,tree1);
			//wc.unlock();
			/*type1 = true;
			type2 = true;
			type3 = true;
			type4 = true;*/
			boolean tagname = false;
			boolean tagfilepath = false;
			if(type == 0)
			{
				if(type4)
				 {
					//System.out.println();
					long tmp = find_resize_file(tr.queue.get(i),tree1);
					if(tmp != -1)
					{
						//System.out.println("file_resize_happend!:"+str+" "+tr.queue.get(i).size+"->"+tmp);
						ans[0][4] ++;
						String stt = "file_resize_happend!:"+str+" "+tr.queue.get(i).size+"->"+tmp + "\n";
						String stt2 = str + "file_resize_happend!:" + ans[0][4]+"\n";
						if(solve_type[4][1])
						   wc.outputt(true,stt);
						if(solve_type[4][2])
						   wc.outputt(false,stt2);
						
					}
				 }
				if(type2)
				 {
					long tmp = find_modify_file(tr.queue.get(i),tree1);
					if(tmp != -1)
					{
						//System.out.println("file_time_modify_happend!:"+str+" "+tr.queue.get(i).modify_time+"->"+tmp);
						ans[0][2] ++;
						String stt = "file_time_modify_happend!:"+str+" "+tr.queue.get(i).modify_time+"->"+tmp + "\n";
						String stt2 =str+ "file_time_modify_happend!:" + ans[0][2]+"\n";
						if(solve_type[2][1])
						   wc.outputt(true,stt);
						if(solve_type[2][2])
						   wc.outputt(false,stt2);
					}
				 }
				if(type1)
				 {
					String pos = find_modify_name(tr.queue.get(i),tree1);
					if(!pos.equals("none"))
					{
						//System.out.println("file_name_modify_happend!:"+str+" "+tr.queue.get(i).path+"->"+pos);
						ans[0][1] ++;
						String stt = "file_name_modify_happend!:"+str+" "+tr.queue.get(i).path+"->"+pos+ "\n";
						String stt2 = str + "file_name_modify_happend!:" + ans[0][1]+"\n";
						if(solve_type[1][1])
						   wc.outputt(true,stt);
						if(solve_type[1][2])
						   wc.outputt(false,stt2);
						str = pos;
					}
					
				 }
				if(type3)
				 {
					String pos = find_modify_path(tr.queue.get(i),tree1);
					if(!pos.equals("none"))
					{
						//System.out.println("file_path_modify_happend!:"+str+" "+tr.queue.get(i).path+"->"+pos);
						ans[0][3] ++;
						String stt = "file_path_modify_happend!:"+str+" "+tr.queue.get(i).path+"->"+pos+ "\n";
						String stt2 = str + "file_path_modify_happend!:" + ans[0][3]+"\n";
						if(solve_type[3][1])
						   wc.outputt(true,stt);
						if(solve_type[3][2])
						   wc.outputt(false,stt2);
						str = pos;
						
					}
				 }
				same_pace(tree1,tr);
			}
			else if(type == 1)
			{
				if(type4)
				{
					for(i = 0;i < tr.queue.size();i++ )
					{
						long tmp = find_resize_file(tr.queue.get(i),tree1);
						if(tmp != -1)
						{
							//System.out.println("dir_resize_happend!:"+tr.queue.get(i).name+" "+tr.queue.get(i).size+"->"+tmp);
							ans[0][4] ++;
							String stt = "dir_resize_happend!:"+tr.queue.get(i).path+" "+tr.queue.get(i).size+"->"+tmp+"\n";
							String stt2 =str +  "dir_resize_happend!:" + ans[0][4]+"\n";
							if(solve_type[4][1])
							   wc.outputt(true,stt);
							if(solve_type[4][2])
							   wc.outputt(false,stt2);
							
						}
					}
					for(i = 0;i < tree1.queue.size();i ++)
					{
						boolean taggg = false;
						for(int j = 0; j < tr.queue.size(); j ++)
						{
							if(tr.queue.get(j).path.equals(tree1.queue.get(i).path)){taggg = true;break;}
						}
						if(!taggg)
						{
							//System.out.println("dir_resize_happend!:"+tree1.queue.get(i).path+" "+"0"+"->"+tree1.queue.get(i).size);
							ans[0][4] ++;
							String stt = "dir_resize_happend!:"+tree1.queue.get(i).path+" "+"0"+"->"+tree1.queue.get(i).size+"\n";
							String stt2 = str + "dir_resize_happend!:" + ans[0][4]+"\n";
							if(solve_type[4][1])
							   wc.outputt(true,stt);
							if(solve_type[4][2])
							   wc.outputt(false,stt2);
						}
					}
				}
				if(type2)
				{
					for(i = 0;i < tr.queue.size();i++ )
					{
						long tmp = find_modify_file(tr.queue.get(i),tree1);
						if(tmp != -1)
						{
							//System.out.println("dir_time_modify_happend!:"+tr.queue.get(i).name+" "+tr.queue.get(i).modify_time+"->"+tmp);
							ans[0][2] ++;
							String stt = "dir_time_modify_happend!:"+tr.queue.get(i).path+" "+tr.queue.get(i).modify_time+"->"+tmp+"\n";
							String stt2 =str+ "dir_time_modify_happend!:" + ans[0][2]+"\n";
							if(solve_type[2][1])
							   wc.outputt(true,stt);
							if(solve_type[2][2])
							   wc.outputt(false,stt2);
						}
					}
				}
				if(type1)
				{
					for(i = 0;i < tr.queue.size();i++ )
					{
						String pos = find_modify_name(tr.queue.get(i),tree1);
							if(!pos.equals("none"))
							{
								//System.out.println("file_name_modify_happend!:"+tr.queue.get(i).name+" "+tr.queue.get(i).path+"->"+pos);
								ans[0][1] ++;
								String stt = "file_name_modify_happend!:"+tr.queue.get(i).name+" "+tr.queue.get(i).path+"->"+pos+"\n";
								String stt2 = str + "file_name_modify_happend!:" + ans[0][1]+"\n";
								if(tr.queue.get(i).type == 0)
								{
								if(solve_type[1][1])
								   wc.outputt(true,stt);
								if(solve_type[1][2])
								   wc.outputt(false,stt2);
								if(solve_type[1][3])
								{
									wc.modify_file(tr.queue.get(i).path,pos);
								}
								}
							}
					}
				}
				if(type3)
				{
					for(i = 0;i < tr.queue.size();i++ )
					{
						String pos = find_modify_path(tr.queue.get(i),tree1);
						if(!pos.equals("none"))
						{
							//System.out.println("dir_path_modify_happend!:"+tr.queue.get(i).name+" "+tr.queue.get(i).path+"->"+pos);
							//str = pos;
							ans[3][1] ++;
							String stt = "dir_path_modify_happend!:"+tr.queue.get(i).name+" "+tr.queue.get(i).path+"->"+pos+"\n";
							String stt2 = str+"dir_path_modify_happend!:" + ans[0][3]+"\n";
							if(tr.queue.get(i).type == 0)
							{
							if(solve_type[3][1])
							   wc.outputt(true,stt);
							if(solve_type[3][2])
							   wc.outputt(false,stt2);
							if(solve_type[3][3])
							{
								wc.modify_file(tr.queue.get(i).path,pos);
								
							}
							}
						}
						
					}
				}
				Tree tree2 = new Tree(str);
				wc.solve(str,tree2);
				same_pace(tree2,tr);
				
			}
			try{
				sleep((long)(10000));
			}catch(Exception e){}	
		}
	 }catch(Exception e)
	 {
		 System.out.println("File illegal modify!");
	 }
	}

}
