import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.learning.lld.policy.DoubleLinkedList;
import org.learning.lld.policy.Node;

import java.util.ArrayList;
import java.util.List;

public class DoubleLinkedListTests {
    private DoubleLinkedList<String>  doubleLinkedList;

    @Before
    public void setup() {
        doubleLinkedList = new DoubleLinkedList<>();
    }

    @Test
    public void addAtFront() {
        String data = "krishna";
        doubleLinkedList.addFront(data);
        Assert.assertEquals(1, doubleLinkedList.size());
        Assert.assertEquals(data, doubleLinkedList.removeLast());
    }

    @Test
    public void removeLast() {
        String[] items = new String[] {"krishna", "radha", "shiva", "parvathi", "vishnu", "ganapati"};

        for (String item : items) {
            doubleLinkedList.addFront(item);
        }

        for (String item : items) {
            Assert.assertEquals(item, doubleLinkedList.removeLast());
        }
    }

    @Test
    public void remove() {
        String[] items = new String[] {"krishna", "radha", "shiva", "parvathi", "vishnu", "ganapati"};
        List<Node<String>> nodes = new ArrayList<>();

        for (String item : items) {
            nodes.add(doubleLinkedList.addFront(item));
        }

        for (Node<String> node : nodes) {
            doubleLinkedList.remove(node);
        }

        Assert.assertEquals(0, doubleLinkedList.size());
    }
}
