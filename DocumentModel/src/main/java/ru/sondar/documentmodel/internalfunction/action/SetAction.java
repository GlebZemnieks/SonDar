package ru.sondar.documentmodel.internalfunction.action;

import ru.sondar.documentmodel.internalfunction.Action;
import ru.sondar.documentmodel.internalfunction.ActionType;
import ru.sondar.documentmodel.internalfunction.exception.IncorrectObjectTypeException;
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
        this.condition = null;
    }

    /**
     * Overloading for set action
     *
     * @return - not use
     */
    @Override
    public Object makeAction() {
        //If condition not null and if condition return True set Value. + !(x||y) == !x&&!y - for avoid NullPointer Exception
        if (!((this.condition != null) && (!(boolean) ((CheckAction) this.condition).makeAction()))) {
            Object temp = navigator.getObject(targetId);
            if (temp instanceof SetterInterface) {
                ((SetterInterface) temp).setValueByAction(value);
            } else {
                throw new IncorrectObjectTypeException("Need implemented interface \"SetterInterface\" in object with id " + this.targetId);
            }
        }
        //Return value not use
        return null;
    }
}
