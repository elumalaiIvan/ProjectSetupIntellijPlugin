package com.vhr.actions;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public abstract class PropertyFileAction extends AnAction {
    String env = "stage";

    @Override
    public void update(@NotNull AnActionEvent e) {
        String selectedFile = ProjectView.getInstance(e.getProject()).getCurrentProjectViewPane().getSelectedDescriptor().toString();
        boolean canShowOption = selectedFile.contains("properties");
        setEnvironment(selectedFile);
        e.getPresentation().setEnabledAndVisible(canShowOption);
    }

    private void setEnvironment(String selectedFile) {
        if (selectedFile.contains("stg")) {
            env = "stage";
        } else if (selectedFile.contains("prod")) {
            env = "prod";
        }
    }

}

