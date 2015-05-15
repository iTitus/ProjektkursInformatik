package projektkurs.command;

import projektkurs.cutscene.CutScene;
import projektkurs.cutscene.CutSceneManager;
import projektkurs.lib.CutScenes;

/**
 * Das CutScene-Start-Kommando.
 */
public class CommandCutScene extends Command {

    @Override
    public EnumCommandResult execute(String[] args) {

        if (args.length != 1) {
            return EnumCommandResult.WRONG_USAGE;
        }

        CutScene cutScene = CutScenes.MAPPINGS.get(args[0]);
        if (cutScene == null) {
            return EnumCommandResult.OBJECT_NOT_FOUND;
        }

        CutSceneManager.startCutScene(cutScene);

        return EnumCommandResult.SUCCESS;
    }

    @Override
    public String[] getAliases() {
        return new String[] { "cs", "c" };
    }

    @Override
    public String getCommand() {
        return "cutscene";
    }

}
