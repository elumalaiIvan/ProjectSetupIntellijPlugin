package com.vhr;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.ProjectManager;

public class VHRApplicationComponent implements ApplicationComponent {

  @Override
  public void initComponent() {
    // Create an instance of MyPluginClass
    VHRPluginListener myPlugin = new VHRPluginListener();

    // Register MyPluginClass as a project manager listener
    ProjectManager.getInstance().addProjectManagerListener(myPlugin);
  }

  @Override
  public void disposeComponent() {
    // Cleanup code here
    System.out.printf("disposeComponent called");
  }

  @Override
  public String getComponentName() {
    return "VHR Component";
  }
}

