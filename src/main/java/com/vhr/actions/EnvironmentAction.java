package com.vhr.actions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import static com.intellij.icons.AllIcons.Actions.Checked_selected;

public class EnvironmentAction extends AnAction {
    private boolean selected;

    public EnvironmentAction(String text, boolean selected) {
        super(text);
        this.selected = selected;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        // handle the action when this environment is selected
        selected = !selected;
    }

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
        // set this environment as selected or unselected based on the current state
        e.getPresentation().setIcon(isSelected() ? Checked_selected : null);
    }

    public boolean isSelected() {
        return selected;
    }
}

