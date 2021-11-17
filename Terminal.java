import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Terminal {
    Parser parser;

    public void chooseCommandAction(){
    	Parser parser=new Parser();
    	String commendN = parser.getCommandName();
    //	String [] argument= parser.getArgs();
    	//System.out.println(commendN);
    	switch(commendN)
    	{
    	case "pwd":
    		pwd();
    	case "echo":
    	//	echo(argument);
    	
    	}
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        // TODO code application logic here
    	
        Scanner in =new Scanner(System.in);
        System.out.println("CLI running... enter commands");
        String temp = in.nextLine();
        Parser parser = new Parser();
        parser.parse(temp);
        System.out.println("done ");
        Terminal term1=new Terminal();
        
        //String [] argument=parser.getArgs();
       // System.out.println(argument);
              // term1.echo(argument);
       // term1.pwd(); //right
        term1.chooseCommandAction();
        
    }
    public void echo(String[] args)
    {
    	System.out.println(args);
    }
     public  void pwd(){
    	 // System Property
        
            String userDirectory = System.getProperty("user.dir");
            System.out.println(userDirectory);
     }

     public  void Is() throws IOException
     {
    	 
    	 String userDirectory = System.getProperty("user.dir");
         System.out.println(userDirectory);
    	// creates a file object
    	    File file = new File(userDirectory);

    	 Files.list(Paths.get(userDirectory))
      .sorted()
      .forEach(System.out::println);

     }
     public void Touch(String dir,String name)
     {
     	//File file = new File(dir);
     	String path = dir;  
         path = path+name;  
         //Instantiate the File class   
         File f1 = new File(path);  
         //Creating a folder using mkdir() method  
         boolean bool = f1.mkdir();  
         if(bool){  
            System.out.println("Folder is created successfully");  
         }else{  
            System.out.println("Error Found!");  
        }         
}
  public void cd()
  {
	  
  }
     }
