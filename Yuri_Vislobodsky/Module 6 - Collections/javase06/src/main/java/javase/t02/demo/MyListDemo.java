package javase.t02.demo;

import javase.t02.mylist.MyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MyList Demo Class
 * Created by Yury Vislobodsky on 03.04.2016.
 */
public class MyListDemo {
    private static Logger logger = LoggerFactory.getLogger(MyListDemo.class);

    private MyListDemo() {}

    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        logger.info("Init : " + list);
        logger.info("Size : " + list.size());
        logger.info("Is empty : " + list.isEmpty());
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(4);
        logger.info("Adding 1,2,3,3,4,4 : " + list);
        list.add(1, 4);
        logger.info("Adding 4 at pos 1 : " + list);
        logger.info("Contains 3 : " + list.contains(3));
        logger.info("Contains 5 : " + list.contains(5));
        logger.info("Size : " + list.size());
        logger.info("Is empty : " + list.isEmpty());
        list.remove(list.lastIndexOf(4));
        logger.info("Remove last 4 : " + list);
        list.remove(new Integer(3));
        logger.info("Remove 3 : " + list);
        list.clear();
        logger.info("Clear : " + list);
    }
}
