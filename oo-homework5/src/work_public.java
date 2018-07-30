import java.io.File;
import java.io.FileOutputStream;


public class work_public {
	boolean cnt = true;
	FileOutputStream oo1,oo2;
	public work_public()
	{
		String st1,st2;
		st1 = "C://ooo/summary.txt";
		st2 = "C://ooo/detail.txt";
		//C://result.txt
		try
		{
			File file1,file2;
			file1 = new File(st1);
			file2 = new File(st2);
			if(!file1.exists())
			{
				boolean gg = file1.createNewFile();
			}
			if(!file2.exists())
			{
				boolean gg = file2.createNewFile();
			}
		oo1 = new FileOutputStream(st1);
		oo2 = new FileOutputStream(st2);
		}catch (Exception e){System.out.println("Wrong???");
		}
	}
	synchronized void lock()
	{
		while (cnt == false)
		  {
			try{  wait();}
			catch (InterruptedException e){}
		  } 
		cnt = false;
	}
	synchronized void outputt(boolean bb,String str)
	{
		while (cnt == false)
		  {
			try{  wait();}
			catch (InterruptedException e){}
		  } 
		cnt = false;
		byte[] buff=new byte[]{}; 
		buff=str.getBytes();  
		if(!bb)
		{
			try{
				 oo1.write(buff,0,buff.length);  
			 }
			 catch(Exception e)
			 {} 
		}
		else
		{
			try{
				 oo2.write(buff,0,buff.length);  
			 }
			 catch(Exception e)
			 {} 	
		}
		cnt = true;
		notifyAll();
	}
	synchronized void unlock()
	{
		cnt = true;
		notifyAll();
	}
	void find(String nowpos,Tree dt,int father)
	{
		try{
			File file = new File(nowpos);
			
			if (!file.isDirectory()) 
			{
			   if(father != -1)
			   {
				data_tree help_father;
				help_father = dt.queue.get(father);
				help_father.size += file.length();
				dt.queue.set(father,help_father);
			   }
			   if(father == -1)
			   {
				   String[] hel = nowpos.split("\\/");
				   String st1 = "";
				   for(int i = 0;i < hel.length-1;i ++)
				   {
					  
					   st1 = st1 + hel[i];
					   if(i != hel.length - 2)
						   st1 = st1 + "/";
				   }
				   //System.out.println(st1);//need_modify
				   find(st1,dt,-1);
			   }
			   else
			   {
				//System.out.println("file:" +nowpos);
				data_tree heldt = new data_tree();
				heldt.father = father;
				heldt.type = 0;
				heldt.path = nowpos;
				heldt.name = file.getName();
				heldt.modify_time = file.lastModified();
				heldt.size = file.length();
				//...need modify!!!!!!!!!!!!!!!
				dt.queue.add(heldt);
				dt.sum ++;
			   }
			}
			else
			{
				//System.out.println("dictionary:" +nowpos);
				data_tree heldt = new data_tree();
				//heldt.father = father;
				heldt.type = 1;
				heldt.path = nowpos;
				heldt.name = file.getName();
				heldt.modify_time = file.lastModified();
				heldt.size = 0;
				dt.queue.add(heldt);
				int selfsum = dt.sum;
				dt.sum ++;
				String[] filelist = file.list();
				for (int i = 0;i < filelist.length; i++)
				{
					find(nowpos+"/"+filelist[i],dt,selfsum);
				}
			}
		}catch(Exception e){}
	}
	synchronized void solve(String str,Tree dt)
	{
		/*while (cnt == false)
		  {
			try{  wait();}
			catch (InterruptedException e){}
		  } */
		while (cnt == false)
		  {
			try{  wait();}
			catch (InterruptedException e){}
		  } 
		cnt = false;
		find(str,dt,-1);
		cnt = true;
		notifyAll();
		/*System.out.println(dt.sum);
		for(int i = 0;i < dt.sum;i ++)
		{
			System.out.println(dt.queue.get(i).path);
		}*/

	}
	synchronized void modify_file(String st1,String st2)
	{
		while (cnt == false)
		  {
			try{  wait();}
			catch (InterruptedException e){}
		  } 
		cnt = false;
		try {  
            File afile = new File(st2);  
            if (afile.renameTo(new File(st1))) {  
                //System.out.println("File is moved successful!");  
            } else {  
                System.out.println("File is failed to move");  
            }  
        } catch (Exception e) {
        	System.out.println("sad");
        }  
		cnt = true;
		notifyAll();
	}
	
	synchronized void solvetest(String st1)
	{
		while (cnt == false)
		  {
			try{  wait();}
			catch (InterruptedException e){}
		  } 
		cnt = false;
		try {  
			String[] help = st1.split("\\*");
            File afile = new File(help[1]);
            
            if(help[0].equals("M"))
            {
            	File bfile = new File(help[2]);
            	if(afile.exists() && !bfile.exists())
            		if (afile.renameTo(bfile)) {  
            			System.out.println("File is renamed successful!");  
            		}
            		else
            		{
            			System.out.println("ERROR! File is renamed unsuccessful!");  
            		}
            }
            else if(help[0].equals("C"))
            {
            	if (help[1].endsWith(File.separator)) {// 判断文件是否为目录
        			System.out.println("ERROR! Cannot create directory!");
        		}
            	else
            	{
            		if (!afile.getParentFile().exists()) 
            		{
            			System.out.println("ERROR! Parent file not exist !!");
            		}
            		else
            		{
            			if(!afile.exists())
            			{
            				if (afile.createNewFile())
            					System.out.println("Create file success!");
            			}
            			FileOutputStream out = new FileOutputStream(help[1]);
            			 byte[] buff=new byte[]{}; 
            			 buff=help[2].getBytes();  
            			 try{
            				 out.write(buff,0,buff.length);  
            			 }
            			 catch(Exception e)
            			 {} 
            		}
            	}
            }
            else if(help[0].equals("D"))
            {
        		if (afile.isFile() && afile.exists()) {
        			afile.delete();// 文件删除
        			System.out.println("File is deleted successful!");  
        		}
        		else
        			System.out.println("ERROR! File is deleted unsuccessful!");  
            }
            else
            {
            	if(afile.exists())
            	{
            		Tree tt = new Tree(help[1]);
            		find(help[1],tt,-1);
            		for(int i = 0;i < tt.queue.size(); i++)
            		{
            			System.out.println(tt.queue.get(i).path +" " + tt.queue.get(i).modify_time + " "+tt.queue.get(i).size);
            		}
            	}
            	else
            	{
            		System.out.println("INVALID PATH");
            	}
            }
        } catch (Exception e) {System.out.println("ERROR HAPPEND!");  }  
		cnt = true;
		notifyAll();
	}
	

}
