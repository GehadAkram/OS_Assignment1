public class Parser {
    String commandName;
    String[] args;

    //This method will divide the input into commandName and args
    //where "input" is the string command entered by the user 
    public boolean parse(String input) {
        String parts[] = input.split("[ ]");
        commandName = parts[0];
        args = new String[(parts.length)-1];
        for (int i = 0; i < parts.length-1; i++) {
            args[i] = parts[i+1];
        }
        return true;

        //else return false if a problem happened??
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArgs() {
        return args;
    }
}
