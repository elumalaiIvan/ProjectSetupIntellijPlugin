package com.vhr.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.vhr.utils.GcpUtil;

public class PushPropertyFileAction extends PropertyFileAction {
    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
        GcpUtil.pushPropertyFiles(actionEvent, env);
    }
}

