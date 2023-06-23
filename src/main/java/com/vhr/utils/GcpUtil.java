package com.vhr.utils;

import com.intellij.openapi.actionSystem.AnActionEvent;

public class GcpUtil {

    public static void gcpLogin(AnActionEvent actionEvent, String env) {
        String command = "vhr gcpLogin" + " --service=" + actionEvent.getProject().getName() + " --environment=" + env;
        execute(actionEvent, command);
    }

    public static void pullConfigurations(AnActionEvent actionEvent, String env) {
        String pullConfigurationCommand = "vhr getProjectConfigurations" + " --service=" + actionEvent.getProject().getName() + " --environment=" + env;
        executeGcpCommand(actionEvent, env, pullConfigurationCommand);
        execute(actionEvent,"vhr pullDBCertificates");
    }

    public static void pullPropertyFiles(AnActionEvent actionEvent, String env) {
        executeGcpCommand(actionEvent, env, "vhr pullPropertyFiles");
    }

    public static void pushPropertyFiles(AnActionEvent actionEvent, String env) {
        executeGcpCommand(actionEvent, env, "vhr pushPropertyFiles");
    }

    public static void createSecret(AnActionEvent actionEvent, String env, String key, String value) {
        executeGcpCommand(actionEvent, env, "vhr pushPropertyFiles");
    }

    private static void execute(AnActionEvent actionEvent, String command) {
        CommandExecutor.executeInTerminal(actionEvent, command);
    }

    private static void executeGcpCommand(AnActionEvent actionEvent,String env, String command) {
        gcpLogin(actionEvent, env);
        execute(actionEvent, command);
    }

}
