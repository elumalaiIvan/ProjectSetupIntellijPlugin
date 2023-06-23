package com.vhr.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class ImportantLink extends AnAction {

    private final String url;
    public ImportantLink(@Nullable String title, @Nullable String link) {
        super(title);
        this.url = link;
    }
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}