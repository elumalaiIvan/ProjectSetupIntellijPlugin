package com.vhr.actions;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.vhr.utils.GcpUtil;
import org.jetbrains.annotations.NotNull;

public class PullConfigurationsAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
        GcpUtil.pullConfigurations(actionEvent, "stage");
        GcpUtil.pullConfigurations(actionEvent, "prod");
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        String selectedFile = ProjectView.getInstance(e.getProject()).getCurrentProjectViewPane().getSelectedDescriptor().toString();
        boolean canShowOption = selectedFile.contains("resources");
        e.getPresentation().setEnabledAndVisible(canShowOption);
    }
}

