package wordcount;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MAIN 
{
	static String test[];
	static String WRITE;
	////////////////////��������/////////////////////
	static String[] read_char=new String[4];
    //���ַ�
    public static void readfile_char(String file)
    {
    	int i_char=0;
        try
         {
            FileReader fr = new FileReader(file);    //frָ���ļ�
            @SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
            String line=br.readLine();
            while (line!= null)
            {       
            	i_char+=line.length();
            	line=br.readLine();
            }
            System.out.print(file);
            System.out.print(",");
            System.out.print(" �ַ�����");
            System.out.println(i_char);
            read_char[0]=file;
            read_char[1]=",";
            read_char[2]=" �ַ��� ";
            WRITE=new StringBuilder().append("\r\n").append(read_char[0]).append(read_char[1]).append(read_char[2]).append(i_char).toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    //������
    public static void readfile_word(String file)
    {
        try
        {
        	int wordNum=1;
        	int flag=1;
            @SuppressWarnings("resource")
			FileReader fr_char = new FileReader(file);
            int ch = 0;
            while((ch=fr_char.read())!=-1)
            {//fr.read()��ȡһ���ֽڣ��жϴ��ֽڵ�ֵΪ-1��ʾ�����ļ�ĩβ�ˡ�
            	if((ch==32||ch==10||ch==44||ch==46||ch==33)&flag==1)//�ո񡢻��С����š���š���̾��
            	{
            		wordNum++;
            		flag=0;
            	}
            	else
            		flag=1;
            }
            System.out.print(file);
            System.out.print(",");
            System.out.print(" ��������");
            System.out.println(wordNum);
            WRITE=new StringBuilder().append("\r\n").append(file).append(",").append(" ��������").append(wordNum).toString();
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    //������
    public static void readfile_line(String file) 
    {
        try
        {
        	int lineNum=0;
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while ((br.readLine()) != null)
            {       
            	lineNum++;
            }
            System.out.print(file);
            System.out.print(",");
            System.out.print(" ������");
            System.out.println(lineNum);
            WRITE=new StringBuilder().append("\r\n").append(file).append(",").append(" ������").append(lineNum).toString();
            
             br.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //д��ָ��������ļ�
    public static void writefile(String file, String arry)
    {        
        try
        {
        	BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));  
            bw.write(arry);
            bw.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    ///////////////////��չ����//////////////////////
    //�������С�ע���кͿ���
    public static void wc2(String file) 
    {
		int codeNum = 0,nullNum=0,explainNum=0,line=0;
    	String content;
        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            content=br.readLine();
            while (content!= null)
            {       
            	line++;
                /////////////////����////////////////////
            	String null_type1="\\s*";
            	String null_type2=".\\s*";
				if(content.matches(null_type1)||content.matches(null_type2))
            	{
            		nullNum++;
            	}
            	/////////////////ע����//////////////////
            	String explain_type1="//.*";            //����б�ܼ������ַ�
            	String explain_type2="//\\s*";          //����б�ܼ������������Ŀո�
            	String explain_type3=".//";             //һ�������ַ���������б��
            	String explain_type4=".//.*";           //һ�������ַ���������б�ܺ������ַ�
            	String explain_type5="/*";              //��/*��ʾע��
            	if(content.matches(explain_type1)||content.matches(explain_type2)||content.matches(explain_type3)||content.matches(explain_type4)||content.matches(explain_type5))
            	{
            		explainNum++;
            	}
				content=br.readLine();
            }
            ///////////////������////////////////////
            codeNum=line-nullNum-explainNum;      
             br.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
       
        WRITE=new StringBuilder().append("\r\n").append(file).append(",").append(" ������").append("/").append(" ����").append("/").append(" ע���У�").append(codeNum).append("/").append(nullNum).append("/").append(explainNum).toString();       
        String WRITE1=new StringBuilder().append(file).append(",").append(" ������").append("/").append(" ����").append("/").append(" ע���У�").append(codeNum).append("/").append(nullNum).append("/").append(explainNum).toString();       
        System.out.println(WRITE1);
    }
    
    //��ͣ�ôʱ�ʱ��������
    public static void stop_word(String file1,String file2) 
    {
        try
        {
        	int stopNum=0;
            FileReader fr = new FileReader(file1);       //�������ļ�
            BufferedReader br = new BufferedReader(fr);
            FileReader fr_s = new FileReader(file2);     //ͣ�ñ�
            @SuppressWarnings("resource")
			BufferedReader br_s = new BufferedReader(fr_s);
            String BR=br.readLine();
            String BR_s=br_s.readLine();
            String b=BR_s;
            while(BR!=null)
            { 
            	int j=0;
            	String[] array=BR.split("\\s+|,");      //split����ÿ������
            	stopNum+=array.length;
            	while(j<array.length)                       //���ڴ������ļ�ÿһ�е�ÿ������
            	{    
            		int k=0;
            		b=BR_s;
                    while (b != null)
                    {       
                    	String[] array_s=BR_s.split("\\s+|,");
                    	while(k<array_s.length)
                    	{
                    		if(array[j].equals(array_s[k]))         //����ͣ�ñ�ÿһ�е�ÿһ�����ʱȽϣ���ͬ�ͼ�һ
                    			stopNum--;
                    		//System.out.print(array_s[k]);
                    		k++;         		
                    	}   
                    	b=br_s.readLine(); 
                    }
                   // System.out.print(array[j]);
                    j++;          
            	}
            	BR=br.readLine();
            }
           // System.out.print(stopNum);
             br.close();
             WRITE=new StringBuilder().append("\r\n").append(file1).append("��������(��ͣ��)��").append(stopNum).toString();       
             System.out.print(file1);
             System.out.print("��������(��ͣ��)��");
             System.out.println(stopNum);
        } 

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    //�����ļ��ڲ��ļ�
	public static void readfiles(String fileDir)
	{
		File file=new File(fileDir);
		test=file.list();
		for(int h=0;h<test.length;h++)
		{
			test[h]=new StringBuilder().append(fileDir).append("\\").append(test[h]).toString();
            
		} 
	}
    
    //������
    public static void main(String[] args) 
    {
    	 if (args.length == 0) {  
            // System.out.println("������main����ʱû��ָ���κβ�����");  
             return;  
         }  
         //System.out.println("������main����ʱָ���Ĳ���������");  
         for (int i = 0; i < args.length; i++) 
         {  

        	 //���ַ�
             if(args[i].equals("-c"))
             {
            	 //����Ҫ���ַ����ļ���
            	 if(args[args.length-2].equals("-o"))
            	 {
            		 if(args[args.length-4].equals("-e"))
            		 {
            			 if(args[0].equals("-s"))
            			 {
            				 readfiles(args[args.length-5]);
            					for(int m=0;m<test.length;m++)
            					{
            						readfile_char(test[m]);
                   				 	writefile(args[args.length-1],WRITE);
            					} 
            			 }
            			 else
            			 {
            				 readfile_char(args[args.length-5]);
            				 writefile(args[args.length-1],WRITE);
            			 }
            		 }
            		 else
            		 {
            			 if(args[0].equals("-s"))
            			 {
            				 readfiles(args[args.length-3]);
            					for(int m=0;m<test.length;m++)
            					{
            						readfile_char(test[m]);
                   				 	writefile(args[args.length-1],WRITE);
            					} 
            			 }
            			 else	 
            			 {
            				 readfile_char(args[args.length-3]);
            				 writefile(args[args.length-1],WRITE);
            			 }
            		 }
            	 }
            	 else
            	 {
            		 if(args[0].equals("-s"))
        			 {
            			 if(args[args.length-2].equals("-e"))
            			 {
            				 readfiles(args[args.length-3]);
         					for(int m=0;m<test.length;m++)
         					{
         						readfile_char(test[m]);
         					} 
            			 }
            			 else
            			 {
            				readfiles(args[args.length-1]);
        					for(int m=0;m<test.length;m++)
        					{
        						readfile_char(test[m]);
        					} 
        				 }
        			 }
            		 else{
            			 if(args[args.length-2].equals("-e"))
            			 {
            				 readfile_char(args[args.length-3]); 
            			 } 
            			 else
            				 readfile_char(args[args.length-1]);
            			 }
            	 }
             }
             //������           
             if(args[i].equals("-w"))
             {
            	 //����Ҫ���ַ����ļ���
            	 if(args[args.length-2].equals("-o"))
            	 {
            		 if(args[args.length-4].equals("-e"))
            		 {
            			 if(args[0].equals("-s"))
            			 {
            				 readfiles(args[args.length-5]);
            					for(int m=0;m<test.length;m++)
            					{
            						stop_word(test[m],args[args.length-3]);
                   				 	writefile(args[args.length-1],WRITE);
            					} 
            			 }
            			 else
            			 {
            				 stop_word(args[args.length-5],args[args.length-3]);
            				 writefile(args[args.length-1],WRITE);
            			 }
            		 }
            		 else
            		 {
            			 if(args[0].equals("-s"))
            			 {
            				 readfiles(args[args.length-3]);
            					for(int m=0;m<test.length;m++)
            					{
            						readfile_word(test[m]);
                   				 	writefile(args[args.length-1],WRITE);
            					} 
            			 }
            			 else	 
            			 {
            				 readfile_word(args[args.length-3]);
            				 writefile(args[args.length-1],WRITE);
            			 }
            		 }
            	 }
            	 else
            	 {
            		 if(args[0].equals("-s"))
        			 {
            			 if(args[args.length-2].equals("-e"))
            			 {
            				 readfiles(args[args.length-3]);
         					for(int m=0;m<test.length;m++)
         					{
         						stop_word(test[m],args[args.length-1]);
         					} 
            			 }
            			 else
            			 {
            				readfiles(args[args.length-1]);
        					for(int m=0;m<test.length;m++)
        					{
        						readfile_word(test[m]);
        					} 
        				 }
        			 }
            		 else{
            			 if(args[args.length-2].equals("-e"))
            			 {
            				 stop_word(args[args.length-3],args[args.length-1]);
            			 } 
            			 else
            				 readfile_word(args[args.length-1]);
            			 }
            	 }
             }
             
             	//������
             if(args[i].equals("-l"))
             {
            	 //����Ҫ���ַ����ļ���
            	 if(args[args.length-2].equals("-o"))
            	 {
            		 if(args[args.length-4].equals("-e"))
            		 {
            			 if(args[0].equals("-s"))
            			 {
            				 readfiles(args[args.length-5]);
            					for(int m=0;m<test.length;m++)
            					{
            						readfile_line(test[m]);
                   				 	writefile(args[args.length-1],WRITE);
            					} 
            			 }
            			 else
            			 {
            				 readfile_line(args[args.length-5]);
            				 writefile(args[args.length-1],WRITE);
            			 }
            		 }
            		 else
            		 {
            			 if(args[0].equals("-s"))
            			 {
            				 readfiles(args[args.length-3]);
            					for(int m=0;m<test.length;m++)
            					{
            						readfile_line(test[m]);
                   				 	writefile(args[args.length-1],WRITE);
            					} 
            			 }
            			 else	 
            			 {
            				 readfile_line(args[args.length-3]);
            				 writefile(args[args.length-1],WRITE);
            			 }
            		 }
            	 }
            	 else
            	 {
            		 if(args[0].equals("-s"))
        			 {
            			 if(args[args.length-2].equals("-e"))
            			 {
            				 readfiles(args[args.length-3]);
         					for(int m=0;m<test.length;m++)
         					{
         						readfile_line(test[m]);
         					} 
            			 }
            			 else
            			 {
            				readfiles(args[args.length-1]);
        					for(int m=0;m<test.length;m++)
        					{
        						readfile_line(test[m]);
        					} 
        				 }
        			 }
            		 else{
            			 if(args[args.length-2].equals("-e"))
            			 {
            				 readfile_line(args[args.length-3]); 
            			 } 
            			 else
            				 readfile_line(args[args.length-1]);
            			 }
            	 }
             }
           //�����С����С�ע����
             if(args[i].equals("-a"))
             {
            	 //����Ҫ���ַ����ļ���
            	 if(args[args.length-2].equals("-o"))
            	 {
            		 if(args[args.length-4].equals("-e"))
            		 {
            			 if(args[0].equals("-s"))
            			 {
            				 readfiles(args[args.length-5]);
            					for(int m=0;m<test.length;m++)
            					{
            						wc2(test[m]);
                   				 	writefile(args[args.length-1],WRITE);
            					} 
            			 }
            			 else
            			 {
            				 wc2(args[args.length-5]);
            				 writefile(args[args.length-1],WRITE);
            			 }
            		 }
            		 else
            		 {
            			 if(args[0].equals("-s"))
            			 {
            				 readfiles(args[args.length-3]);
            					for(int m=0;m<test.length;m++)
            					{
            						wc2(test[m]);
                   				 	writefile(args[args.length-1],WRITE);
            					} 
            			 }
            			 else	 
            			 {
            				 wc2(args[args.length-3]);
            				 writefile(args[args.length-1],WRITE);
            			 }
            		 }
            	 }
            	 else
            	 {
            		 if(args[0].equals("-s"))
        			 {
            			 if(args[args.length-2].equals("-e"))
            			 {
            				 readfiles(args[args.length-3]);
         					for(int m=0;m<test.length;m++)
         					{
         						wc2(test[m]);
         					} 
            			 }
            			 else
            			 {
            				readfiles(args[args.length-1]);
        					for(int m=0;m<test.length;m++)
        					{
        						wc2(test[m]);
        					} 
        				 }
        			 }
            		 else{
            			 if(args[args.length-2].equals("-e"))
            			 {
            				 wc2(args[args.length-3]); 
            			 } 
            			 else
            				 wc2(args[args.length-1]);
            			 }
            	 }
             }
    }
         
    }
}
    
