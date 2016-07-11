package ru.sondar.documentmodel.internalfunction.action;

import ru.sondar.documentmodel.internalfunction.Action;
import ru.sondar.documentmodel.internalfunction.ActionType;
import ru.sondar.documentmodel.internalfunction.exception.IncorrectObjectTypeException;
import ru.sondar.documentmodel.exception.ObjectNotFountException;
import ru.sondar.documentmodel.internalfunction.interfaces.NavigatorInterface;
import ru.sondar.documentmodel.internalfunction.interfaces.ValueCheckerInterface;

/**
 *
 * @author GlebZemnieks
 */
public class CheckAction extends Action {

    public CheckAction(NavigatorInterface navigator) {
        this.navigator = navigator;
        this.isActive = false;
        this.actionType = ActionType.checkAction;
        this.actionName = "";
    }

    @Override
    public Object makeAction() {
        Object temp = navigator.getObject(targetId);
        if (temp == null) {
            throw new ObjectNotFountException();
        }
        if (temp instanceof ValueCheckerInterface) {
            return ((ValueCheckerInterface) temp).ifValue(value);
        } else {
            throw new IncorrectObjectTypeException("Need implemented interface \"ValueCheckerInterface\" in object with id " + this.targetId);
        }
    }
}
