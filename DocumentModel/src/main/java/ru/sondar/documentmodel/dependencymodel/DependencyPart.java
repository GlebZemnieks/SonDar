package ru.sondar.documentmodel.dependencymodel;

import java.util.ArrayList;
import java.util.Iterator;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.exception.ObjectNotFountException;

/**
 * Object for store dependency item and iteration for them
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public class DependencyPart implements Iterable<DependencyItem> {

    /**
     * Tag for print and parse object
     */
    public static String Dependency_MainTag = "DependencyPart";

    /**
     * Dependency list object
     */
    private ArrayList<DependencyItem> dependencyList = new ArrayList<>();

    public void addDependencyItem(String name, int cellId) {
        this.dependencyList.add(new DependencyItem(name, cellId));
    }

    public void addDependencyItemWithValidation(SDSequenceObject sequence, String name, int cellId) {
        if (!sequence.isObjectWithNameExist(name)) {
            throw new ObjectNotFountException("Try to set dependency to object with name \"" + name + "\" fail. Object not found.");
        }
        addDependencyItem(name, cellId);
    }

    public ArrayList<DependencyItem> getDependencyList() {
        return this.dependencyList;
    }

    public void setDependencyList(ArrayList<DependencyItem> list) {
        this.dependencyList = list;
    }

    @Override
    public Iterator<DependencyItem> iterator() {
        return this.dependencyList.iterator();
    }

    @Override
    public String toString() {
        String temp = "Dependency : \n";
        for (int i = 0; i < this.dependencyList.size(); i++) {
            temp += this.dependencyList.get(i).toString() + "\n";
        }
        return temp;
    }

}
