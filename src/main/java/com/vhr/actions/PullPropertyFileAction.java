package com.vhr.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.vhr.utils.GcpUtil;

public class PullPropertyFileAction extends PropertyFileAction {
    
    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
        GcpUtil.pullPropertyFiles(actionEvent, env);
    }

}

