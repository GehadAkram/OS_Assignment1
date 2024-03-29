import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Terminal {

    Parser parser;

    public void echo(String arg) {
        System.out.println(arg);
    }

    public void pwd() {
        String userDirectory = System.getProperty("user.dir");
        System.out.println(userDirectory);
    }
    
    public void cd() {
        String userHomeDir = System.getProperty("user.home");
        System.out.printf("The User Home Directory is %s", userHomeDir);
    }

    public void ChangeDir(String dir) throws IOException {
        File file = new File(".");
        System.out.println("Current Working Directory: " + file.getAbsolutePath());
        System.setProperty("user.dir", dir);
        System.out.println("New Current Working Directory: " + file.getAbsolutePath());
    }

    public void cd(String dir) {
        // if (dir !="..")
        //{
        Path path = Paths.get(System.getProperty("user.dir"));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.print("cdd");

        // }else
        {
            try {
                ChangeDir(dir);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void ls() throws IOException {
        String userDirectory = System.getProperty("user.dir");
        System.out.println(userDirectory);
        // creates a file object
        File file = new File(userDirectory);
        Files.list(Paths.get(userDirectory)).sorted().forEach(System.out::println);
    }
    
    public void mkdir(String arg) {
        Path path = Paths.get(arg);
        path = path.toAbsolutePath();
        System.out.println(path.toString());
        new File(path.toString()).mkdir();
    }
    
    public void rmdir(String arg) {
        File directory = new File(arg);
        if (directory.exists()) {
            if ("*".equals(arg)) {
                System.out.println(System.getProperty("user.dir"));
                new File(System.getProperty("user.dir")).delete();
            } else {
                Path path = Paths.get(arg);
                path = path.toAbsolutePath();
                System.out.println(path.toString());
                new File(path.toString()).delete();
            }
        } else {
            System.out.println("directory doesn't exist, no action will be taken");
        }
    }
    
    public void touch(String dir, String name) {
        //File file = new File(dir);
        String path = dir;
        path = path + name;
        //Instantiate the File class   
        File f1 = new File(path);
        //Creating a folder using mkdir() method  
        boolean bool = f1.mkdir();
        if (bool) {
            System.out.println("Folder is created successfully");
        } else {
            System.out.println("Error Found!");
        }
    }
    
    public void cp (String from, String to) throws IOException{
        File copyFrom = new File(from);
        File copyTo = new File(to);
        Files.copy(copyFrom.toPath(),copyTo.toPath());
    }
    
    public void cpr(String from, String to) throws IOException {
        Files.walk(Paths.get(from))
                .forEach(source -> {
                    Path destination = Paths.get(to, source.toString()
                            .substring(from.length()));
                    try {
                        Files.copy(source, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
    
    
    public void chooseCommandAction(String input) {
        parser = new Parser();
        parser.parse(input);
        switch (parser.getCommandName()) {
            case "pwd": {
                pwd();
                break;
            }
            case "echo": {
                echo(parser.getArgs()[0]);
                break;
            }
            case "cd": {
                if(parser.getArgs().length == 0){
                    cd();
                }
                else 
                {
                    cd(parser.getArgs()[0]);
                }
                break;
            }
            case "ls": {
                try {
                    ls();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            }
            case "mkdir": {
                for(int i=0; i< parser.getArgs().length; i++){
                    mkdir(parser.getArgs()[i]);
                }
                break;
            }
            case "rmdir":{
                rmdir(parser.getArgs()[0]);
                break;
            }
            case "touch": {
                touch(parser.getArgs()[0],parser.getArgs()[1]);
                break;
            }
            case "cp": {
                try {
                    cp(parser.getArgs()[0], parser.getArgs()[1]);
                } catch (IOException ex) {
                    Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "cp-r":{
                try {
                    cpr(parser.getArgs()[0], parser.getArgs()[1]);
                } catch (IOException ex) {
                    Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "exit":{
                System.exit(0);
                break;
            }
            default :{
                System.out.println("Wrong command");
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Scanner in = new Scanner(System.in);
        Terminal terminal = new Terminal();
        System.out.println("CLI running... enter commands");
        String temp = null;
        while (!"exit".equals(temp)) {
            temp = in.nextLine();
            terminal.chooseCommandAction(temp);
            System.out.println("done ");
        }
    }

}
