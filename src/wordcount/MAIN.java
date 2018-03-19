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
	////////////////////基本功能/////////////////////
	static String[] read_char=new String[4];
    //数字符
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
            System.out.print(" 字符数：");
            System.out.print(i_char);
            read_char[0]=file;
            read_char[1]=",";
            read_char[2]=" 字符数 ";
            WRITE=new StringBuilder().append("\r\n").append(read_char[0]).append(read_char[1]).append(read_char[2]).append(i_char).toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    //数单词
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
            {//fr.read()读取一个字节，判断此字节的值为-1表示读到文件末尾了。
            	if((ch==32||ch==10||ch==44||ch==46||ch==33)&flag==1)//空格、换行、逗号、句号、感叹号
            	{
            		wordNum++;
            		flag=0;
            	}
            	else
            		flag=1;
            }
            System.out.print(file);
            System.out.print(",");
            System.out.print(" 单词数：");
            System.out.print(wordNum);
            WRITE=new StringBuilder().append("\r\n").append(file).append(",").append(" 单词数：").append(wordNum).toString();
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    //数行数
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
            System.out.print(" 行数：");
            System.out.print(lineNum);
            WRITE=new StringBuilder().append("\r\n").append(file).append(",").append(" 行数：").append(lineNum).toString();
            
             br.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //写入指定的输出文件
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
    
    ///////////////////扩展功能//////////////////////
    //数代码行、注释行和空行
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
                /////////////////空行////////////////////
            	String null_type1="\\s*";
            	String null_type2=".\\s*";
				if(content.matches(null_type1)||content.matches(null_type2))
            	{
            		nullNum++;
            	}
            	/////////////////注释行//////////////////
            	String explain_type1="//.*";            //两个斜杠加任意字符
            	String explain_type2="//\\s*";          //两个斜杠加上任意数量的空格
            	String explain_type3=".//";             //一个任意字符加上两个斜杠
            	String explain_type4=".//.*";           //一个任意字符加上两个斜杠和任意字符
            	String explain_type5="/*";              //用/*表示注释
            	if(content.matches(explain_type1)||content.matches(explain_type2)||content.matches(explain_type3)||content.matches(explain_type4)||content.matches(explain_type5))
            	{
            		explainNum++;
            	}
				content=br.readLine();
            }
            ///////////////代码行////////////////////
            codeNum=line-nullNum-explainNum;      
             br.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        WRITE=new StringBuilder().append("\r\n").append(file).append(",").append(" 代码行").append("/").append(" 空行").append("/").append(" 注释行：").append(codeNum).append("/").append(nullNum).append("/").append(explainNum).toString();       
    }

    
    //主函数
    public static void main(String[] args) 
    {
    	 if (args.length == 0) {  
            // System.out.println("您调用main方法时没有指定任何参数！");  
             return;  
         }  
         //System.out.println("您调用main方法时指定的参数包括：");  
         for (int i = 0; i < args.length; i++) 
         {  

        	 //数字符
             if(args[i].equals("-c"))
             {
            	 //输入要数字符的文件名
            	 if(args[args.length-2].equals("-o"))
            	 {
            				 readfile_char(args[args.length-3]);
            				 writefile(args[args.length-1],WRITE);
            			
            		 }
            	}
            	 else	 
            			 readfile_char(args[args.length-1]);
             
             //数单词
             if(args[i].equals("-w"))
             {
            	 //输入要数单词的文件名
            	 if(args[args.length-2].equals("-o"))
            	 {
            		 readfile_word(args[args.length-3]);
            		 writefile(args[args.length-1],WRITE);
            	 }
            			 
            		 readfile_word(args[args.length-1]);
            	 
    	     }
             	//数行数
             if(args[i].equals("-l"))
             {
            	 //输入要数字符的文件名
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
            	 //输入要数字符的文件名
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
