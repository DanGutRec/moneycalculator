package sofware.ulpgc.project.control;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Builder> commands;

    public CommandFactory() {
        this.commands = new HashMap<>();
    }
    public Builder getCommand(String command) {
        return commands.get(command);
    }
    public CommandFactory addCommand(String command, Builder builder) {
        commands.put(command, builder);
        return this;
    }
    
    interface  Builder {
        Command build();
    }
}
