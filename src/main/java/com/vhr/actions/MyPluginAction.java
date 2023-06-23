package com.vhr.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.vhr.actions.groups.Environment;
import com.vhr.utils.CommandExecutor;

import java.io.File;

public final class MyPluginAction extends AnAction {

    private final File file;
    private final Environment environment;

    public MyPluginAction(File file, String fileNameWithoutExtension, Environment environment) {
        super(fileNameWithoutExtension);
        this.file = file;
        this.environment = environment;
    }
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        String serviceCommand = "sh " + file.getAbsolutePath();
        // Do something with the file
        CommandExecutor.executeInTerminal(anActionEvent, "vhr config set --env=" + environment.name().toLowerCase());
        CommandExecutor.executeInTerminal(anActionEvent, serviceCommand);
    }
}

