import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

// pwd // cd // echo // touch finish ///problem with current directory 
public class Terminal {
    Parser parser;

    public void chooseCommandAction(String input){
     parser=new Parser();
     parser.parse(input);
    	String commendN = parser.getCommandName();
    	//if ((parser.getArgs() !=null)) {
    	String argument1 = parser.getArgs()[0];
    	String argument2 = parser.getArgs()[1];

    	switch(commendN)
    	{
    	case "pwd":
    	{
    		pwd();
    		
    		break;
    		}
    	case "echo":
    	{
    		echo(argument1);
    		break;
    	}	
    	case"exit":
    	{
    	
    		break;
    	}
    	case"touch":
    	{
    	   Touch(argument1,argument2);
    		break;
    	}
    	case"is":
    	{
    	  try {
			Is();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		break;
    	}
    	case "cd":
    		
    	{
    		/*if(argument1=="..")
    		{
    			cd(argument1);
    		}else if(argument1==null)
    		{
    			
    		}*/
    		cd(argument1);
    		break;
    	}
    }}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException {
        // TODO code application logic here
    	
        Scanner in =new Scanner(System.in);
        System.out.println("CLI running... enter commands");
        String temp = null;
      while (temp !="exit" ) { //
        temp = in.nextLine();
        Parser parser = new Parser();
        parser.parse(temp);
        System.out.println("done ");
        Terminal term1=new Terminal();
        
        //String [] argument=parser.getArgs();
       // System.out.println(argument);
              // term1.echo(argument);
       // term1.pwd(); //right
        term1.chooseCommandAction(temp);
      } 
    }
    public void echo(String args)
    {
     /*  for (int i = 0; i < args.length; i++) {
    	System.out.println(args);
    	}*/
    	System.out.println(args);
    }
     public  void pwd(){
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
     
  public void cd(String dir)
  {
	  File f1 = new File(dir);  
      
  }
  public void cd()
  {
	  String userHomeDir = System.getProperty("user.home");
      System.out.printf("The User Home Directory is %s", userHomeDir);
  }
     }
