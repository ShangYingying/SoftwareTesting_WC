package wordcount;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
            FileReader fr = new FileReader(file);
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
            System.out.print(i_char);
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
            System.out.print(wordNum);
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
            System.out.print(lineNum);
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
            				 readfile_char(args[args.length-3]);
            				 writefile(args[args.length-1],WRITE);
            			
            		 }
            	}
            	 else	 
            			 readfile_char(args[args.length-1]);
             
             //������
             if(args[i].equals("-w"))
             {
            	 //����Ҫ�����ʵ��ļ���
            	 if(args[args.length-2].equals("-o"))
            	 {
            		 readfile_word(args[args.length-3]);
            		 writefile(args[args.length-1],WRITE);
            	 }
            			 
            		 readfile_word(args[args.length-1]);
            	 
    	     }
             	//������
             if(args[i].equals("-l"))
             {
            	 //����Ҫ���ַ����ļ���
            	 if(args[args.length-2].equals("-o"))
            	 {
            		 readfile_line(args[args.length-3]);
            		 writefile(args[args.length-1],WRITE);
            	}
            		
            		 else
            			 readfile_line(args[args.length-1]);
            }
             if(args[i].equals("-a"))
             {
            	 //����Ҫ���ַ����ļ���
            	 if(args[args.length-2].equals("-o"))
            	 {
            		 wc2(args[args.length-3]);
            		 writefile(args[args.length-1],WRITE);
            	}
            		
            		 else
            			 wc2(args[args.length-1]);
            }
             }
           
                       
         }

}
