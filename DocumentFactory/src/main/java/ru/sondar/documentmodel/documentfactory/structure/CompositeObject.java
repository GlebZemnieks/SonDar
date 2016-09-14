package ru.sondar.documentmodel.documentfactory.structure;

import java.util.ArrayList;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.objectmodel.SDMainObject;

/**
 *
 * @author GlebZemnieks
 */
public class CompositeObject {

    private ArrayList<SDMainObject> objects;

    public CompositeObject() {
        this.objects = new ArrayList<>();
    }

    public final void addObject(SDMainObject object) {
        this.objects.add(object);
    }

    public void addObjectListToSequence(SDSequenceObject sequenceObject) {
        for (SDMainObject object : this.objects) {
            sequenceObject.AddXMLObject(object);
        }
    }
}
