package projektkurs.command;

import projektkurs.Main;

public class CommandReload extends Command {

    @Override
    public EnumCommandResult execute(String[] args) {
        Main.getLevel().getMap().getEntityList().clear();
        Main.getLevel().getMap().getStoryManager().getTriggerMap().clear();
        Main.startLevel(Main.getLevel());
        return EnumCommandResult.SUCCESS;
    }

    @Override
    public String getCommand() {
        return "reload";
    }

}
