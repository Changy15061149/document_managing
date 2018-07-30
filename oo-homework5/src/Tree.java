import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class Tree {
	ArrayList<data_tree> queue = new ArrayList<data_tree>();
	String fn;
	int sum = 0;
	public Tree(String file_name)
	{
		fn = file_name;
	}
	void build_tree(String nowpos,int father)
	{
		try{
			File file = new File(nowpos);
			
			if (!file.isDirectory()) 
			{
			   if(father != -1)
			   {
				data_tree help_father = new data_tree();
				help_father = queue.get(father);
				help_father.size += file.length();
				queue.set(father,help_father);
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
				   build_tree(st1,-1);
			   }
			   else
			   {
				//System.out.println("file:" +nowpos+' '+father);
				data_tree heldt = new data_tree();
				heldt.father = father;
				heldt.type = 0;
				heldt.path = nowpos;
				heldt.name = file.getName();
				heldt.modify_time = file.lastModified();
				heldt.size = file.length();
				//...need modify!!!!!!!!!!!!!!!
				queue.add(heldt);
				sum ++;
			   }
			}
			else
			{
				//System.out.println("dictionary:" +nowpos);
				data_tree heldt = new data_tree();
				heldt.father = father;
				heldt.type = 1;
				heldt.path = nowpos;
				heldt.name = file.getName();
				heldt.modify_time = file.lastModified();
				heldt.size = 0;
				queue.add(heldt);
				int selfsum = sum;
				sum ++;
				String[] filelist = file.list();
				for (int i = 0;i < filelist.length; i++)
				{
					build_tree(nowpos+"/"+filelist[i],selfsum);
				}
			}
		}catch(Exception e){}
	}
	void tree_modify(String stt,int tag,int value,String va)
	{
		int i = 0;
		for(i = 0;i < queue.size();i ++)
		{
			if(queue.get(i).name.equals(stt))
			{
				if(tag == 1)
				{
					
				}
				else if(tag == 2)
				{
					
				}
			}
		}
	}
	void tree_add(String stt)
	{
		data_tree heldt = new data_tree();
		//heldt.father = father;
		File file = new File(stt);
		heldt.type = file.isDirectory()?0:1;
		heldt.name = stt;
		//need modify
		queue.add(heldt);
	}

}
