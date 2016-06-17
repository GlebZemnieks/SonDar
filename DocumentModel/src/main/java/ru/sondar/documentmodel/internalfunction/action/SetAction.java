package ru.sondar.documentmodel.internalfunction.action;

import ru.sondar.documentmodel.internalfunction.Action;
import ru.sondar.documentmodel.internalfunction.ActionType;
import ru.sondar.documentmodel.internalfunction.interfaces.NavigatorInterface;
import ru.sondar.documentmodel.internalfunction.interfaces.SetterInterface;

/**
 * Active action to set some value to target object
 *
 * @author GlebZemnieks
 */
public class SetAction extends Action {

    private Action condition = null;

    public Action setCondition(Action condition) {
        this.condition = condition;
        return this;
    }

    public SetAction(NavigatorInterface navigator) {
        this.navigator = navigator;
        this.isActive = true;
        this.actionType = ActionType.setAction;
    }

    @Override
    public Object makeAction() {
        if (this.condition != null) {
            if ((boolean) ((CheckAction) this.condition).makeAction()) {
                ((SetterInterface) navigator.getObject(targetId)).setValue(value);
            }
        } else {
            Object temp = navigator.getObject(targetId);
            SetterInterface temp2 = ((SetterInterface) temp);
            temp2.setValue(value);
        }
        return null;
    }
}
