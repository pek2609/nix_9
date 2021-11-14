package ua.com.alevel.examples.constructors;

import ua.com.alevel.MathSet;
import ua.com.alevel.TaskRunner;

public class DefaultConstructor implements TaskRunner {

    @Override
    public void run() {
        MathSet mathSet = new MathSet();
        System.out.println("mathSet = " + mathSet.toString());
    }
}
