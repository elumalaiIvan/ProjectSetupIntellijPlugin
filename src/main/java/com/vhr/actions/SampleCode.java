package com.vhr.actions;

public class SampleCode {

//    IntelliJ Plugin Get Code From Current Open File

//    in what context? If you are inside an action, you can simply take everything from the ActionEvent, for example:
//
//            e.getData(LangDataKeys.EDITOR).getDocument().getText();
//(When e is AnActionEvent).
//
//    Otherwise, you can get it from the project:
//
//            FileEditorManager.getInstance(project).getSelectedTextEditor().getDocument().getText();


//    Progress indicators
//    https://jetbrains.design/intellij/controls/progress_indicators/


//    @Override
//    public void actionPerformed(@NotNull AnActionEvent event) {
//        // Using the event, create and show a dialog
//        Project currentProject = event.getProject();
//        StringBuilder message =
//                new StringBuilder(event.getPresentation().getText() + " Selected!");
//        // If an element is selected in the editor, add info about it.
//        Navigatable selectedElement = event.getData(CommonDataKeys.NAVIGATABLE);
//        if (selectedElement != null) {
//            message.append("\nSelected Element: ").append(selectedElement);
//        }
//        String title = event.getPresentation().getDescription();
//        Messages.showMessageDialog(
//                currentProject,
//                message.toString(),
//                title,
//                Messages.getInformationIcon());
//    }

//    @Override
//    public void update(@NotNull AnActionEvent event) {
//        // Set the availability based on whether a project is open
//        Project currentProject = event.getProject();
//        event.getPresentation().setEnabledAndVisible(currentProject != null);
//    }
}