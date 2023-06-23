package com.vhr;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import com.vhr.actions.EnvironmentAction;
import com.vhr.actions.groups.ImportantLinksActionGroup;
import com.vhr.actions.groups.PostManActionGroup;

import java.util.Arrays;

public class VHRPluginListener implements ProjectManagerListener {
  private boolean isVHRMenuAdded = false;
  private final String[] allowedProjects = {"vhr-schedules-service", "vhr-schedules-processor", "vhr-core-service"};
  private final ActionManager actionManager = ActionManager.getInstance();
  @Override
  public void projectOpened(Project project) {
    System.out.println("project opened called" + isVHRMenuAdded);
    // Check if the project meets the criteria to show the VHR menu
    if (!isVHRMenuAdded && project != null && isProjectAllowed(project)) {
      // Get the action manager
      DefaultActionGroup mainMenu = (DefaultActionGroup) actionManager.getAction("MainMenu");
      DefaultActionGroup vhrMenu = new DefaultActionGroup("VHR", true);

      actionManager.registerAction("VHRMenu", vhrMenu);

      // Create the VHR menu
      DefaultActionGroup postManActionGroup = new PostManActionGroup("Postman", true);
      vhrMenu.add(postManActionGroup);

      //Create the important Links menu
      DefaultActionGroup importantLinksGroup = new ImportantLinksActionGroup("Important Links", true);
      vhrMenu.add(importantLinksGroup);

      mainMenu.add(vhrMenu, new Constraints(Anchor.BEFORE, "HelpMenu"));

      isVHRMenuAdded = true;
    }
  }

  // Recursively add sub-folders as sub-action groups
  @Override
  public void projectClosed(Project project) {
    if (isProjectAllowed(project)) {
      AnAction action = actionManager.getAction("VHRMenu");
      action.getTemplatePresentation().setEnabledAndVisible(false);
    }
  }

  private boolean isProjectAllowed(Project project) {
    return Arrays.stream(allowedProjects).anyMatch(p -> p.equals(project.getName()));
  }
}
