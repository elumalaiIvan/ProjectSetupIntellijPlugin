package com.vhr.utils;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import org.jetbrains.plugins.terminal.ShellTerminalWidget;
import org.jetbrains.plugins.terminal.TerminalToolWindowFactory;
import org.jetbrains.plugins.terminal.TerminalView;

import java.io.*;
import java.util.Objects;

public class CommandExecutor {


    private static final String TAB_NAME = "GCP";

    public static void execute1(String cmdToExecute) {

        ProcessBuilder pb = new ProcessBuilder(cmdToExecute);
        java.lang.Process process = null;

        try {
            process = pb.start();
        } catch (IOException ex) {
            //--
        }
        OutputStream os = process.getOutputStream();
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os)));

        final InputStream is = process.getInputStream();
        new Thread(() -> {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
            }
        }).start();

//        for (String arg : args) {
//            pw.println(arg);
//        }

        pw.close();

        int returnCode = -1;
        try {
            returnCode = process.waitFor();
        } catch (InterruptedException ex) {
            //--
        }
        System.out.println(returnCode);
    }


    public static void execute2(String cmdToExecute) {
        try {

            String command = cmdToExecute;

            ProcessBuilder pb = new ProcessBuilder("sh", "-c", command);

            pb.directory(new File(System.getProperty("user.home")));

            pb.redirectErrorStream(true);

            Process process = pb.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println(
                        "**************************** The Output is ******************************");
                System.out.println(output);
            } else {
                //show the failure popup
                System.out.println(
                        "**************************** Failed ******************************");
            }
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
    public static void executeInTerminal(AnActionEvent event, String cmdToExecute) {

        try {
            Project project = event.getProject();
            TerminalView terminalView = TerminalView.getInstance(project);
            Content content = Objects.requireNonNull(ToolWindowManager
                            .getInstance(project)
                            .getToolWindow(TerminalToolWindowFactory.TOOL_WINDOW_ID))
                    .getContentManager().findContent(TAB_NAME);

            ShellTerminalWidget widget = content != null ?
                    (ShellTerminalWidget) TerminalView.getWidgetByContent(content)
                    : terminalView.createLocalShellWidget(project.getBasePath(), TAB_NAME);

            assert widget != null;
            widget.executeCommand(cmdToExecute);

            // gets the terminal window focus
            Objects.requireNonNull(ToolWindowManager.getInstance(project).getToolWindow(TerminalToolWindowFactory.TOOL_WINDOW_ID)).activate(null);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}