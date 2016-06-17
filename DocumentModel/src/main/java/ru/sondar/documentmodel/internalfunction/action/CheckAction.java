package ru.sondar.documentmodel.internalfunction.action;

import ru.sondar.documentmodel.internalfunction.Action;
import ru.sondar.documentmodel.internalfunction.ActionType;
import ru.sondar.documentmodel.internalfunction.exception.ObjectNotFountException;
import ru.sondar.documentmodel.internalfunction.interfaces.NavigatorInterface;
import ru.sondar.documentmodel.internalfunction.interfaces.ValueCheckerInterface;

/**
 *
 * @author GlebZemnieks
 */
public class CheckAction extends Action {

    public CheckAction(NavigatorInterface navigator, String name) {
        this.navigator = navigator;
        this.isActive = false;
        this.actionType = ActionType.checkAction;
        this.actionName = name;
    }

    @Override
    public Object makeAction() {
        Object temp = navigator.getObject(targetId);
        if (temp == null) {
            throw new ObjectNotFountException();
        }
        ValueCheckerInterface temp2 = ((ValueCheckerInterface) temp);
        return temp2.ifValue(value);
    }
}
