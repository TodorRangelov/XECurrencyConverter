package console;

import domain.commands.ConvertCommand;
import domain.commands.EndCommand;
import domain.io.Logger;

public class ConsoleCommandExecutor {

    public void execute(String[] args) {

        switch (args[0]) {
            case "END":
                new EndCommand().execute();
                break;
            case "CONVERT":
                new ConvertCommand(args).execute();
                break;
            default:
                Logger logger = new ConsoleLogger();
                logger.logLine("incorrect command");
        }
    }

}
