package com.vhr.actions.groups;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.vhr.actions.ImportantLink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ImportantLinksActionGroup extends DefaultActionGroup {

    public ImportantLinksActionGroup(String displayText, boolean popup) {
        super(displayText, popup);
    }

    @Override
    public AnAction @NotNull [] getChildren(@Nullable AnActionEvent e) {
        String IMPORTANT_LINKS_PATH = System.getenv("VHR_REPOS_PATH") + "/VHRSetup/Links/ImportantLinks.json";

        return Objects.requireNonNull(processJsonAndCreateActions(IMPORTANT_LINKS_PATH)).toArray(new AnAction[0]);
    }

    private List<AnAction> processJsonAndCreateActions(String Link) {
        try {
            List<AnAction> children = new ArrayList<>();
            JSONObject links = (JSONObject) new JSONParser().parse(new FileReader(Link));
            links.keySet().forEach(key -> {
                Object value = links.get(key);
                if (value instanceof JSONObject) {
                    DefaultActionGroup subActionGroup = new DefaultActionGroup(key.toString(), true);
                    JSONObject subMenuLinks = (JSONObject) value;
                    subMenuLinks.keySet().forEach(subKey -> {
                        String url = (String) subMenuLinks.get(subKey);
                        ImportantLink action = new ImportantLink(subKey.toString(), url);
                        subActionGroup.add(action);
                    });
                    children.add(subActionGroup);
                } else if(value instanceof String) {
                    String url = (String) links.get(key);
                    ImportantLink action = new ImportantLink(key.toString(), url);
                    children.add(action);
                }
            });
            return children;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
