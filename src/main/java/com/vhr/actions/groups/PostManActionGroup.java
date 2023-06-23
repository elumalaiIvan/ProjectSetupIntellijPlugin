package com.vhr.actions.groups;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.vhr.actions.MyPluginAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.vhr.actions.groups.Environment.PROD;
import static com.vhr.actions.groups.Environment.STAGE;

public final class PostManActionGroup extends DefaultActionGroup {

    public PostManActionGroup(String displayText, boolean popup) {
       super(displayText, popup);
    }

    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
        String POSTMAN_COLLECTIONS_PATH = System.getenv("POSTMAN_PATH") + "/collections";
        File postManDirectory = new File(POSTMAN_COLLECTIONS_PATH);
        DefaultActionGroup stageActionGroup = new DefaultActionGroup("Stage", true);
        DefaultActionGroup prodActionGroup = new DefaultActionGroup("Prod", true);
        stageActionGroup.addAll(processFoldersAndCreateActions(postManDirectory, STAGE));
        prodActionGroup.addAll(processFoldersAndCreateActions(postManDirectory, PROD));

        return new AnAction[]{stageActionGroup, prodActionGroup};
    }

    private List<AnAction> processFoldersAndCreateActions(File postManDirectory, Environment environment) {
        List<AnAction> children = new ArrayList<>();
        for (File subDirectory : getSubDirectories(postManDirectory)) {
            DefaultActionGroup subActionGroup = new DefaultActionGroup(subDirectory.getName(), true);
            subActionGroup.addAll(processFoldersAndCreateActions(subDirectory, environment));
            children.add(subActionGroup);
        }
        // Add files as actions
        for (File file : getFiles(postManDirectory)) {
            MyPluginAction action = new MyPluginAction(file, getFileNameWithoutExtension(file.getName()), environment);
            children.add(action);
        }
        return children;
    }

    private String getFileNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex > 0) ? fileName.substring(0, dotIndex) : fileName;
    }

    @NotNull
    private File[] getSubDirectories(File directory) {
        return Objects.requireNonNull(directory.listFiles(File::isDirectory));
    }

    @NotNull
    private File[] getFiles(File directory) {
        return Objects.requireNonNull(directory.listFiles(File::isFile));
    }
}

