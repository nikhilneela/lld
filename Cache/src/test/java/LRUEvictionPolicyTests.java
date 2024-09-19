import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.learning.lld.policy.LRUEvictionPolicy;

import java.util.Random;

public class LRUEvictionPolicyTests {
    private LRUEvictionPolicy<String> lruEvictionPolicy;

    @Before
    public void setup() {
        lruEvictionPolicy = new LRUEvictionPolicy<>();
    }

    @Test
    public void addKeys() {
        String[] keys = new String[] {"shiva", "parvathi", "krishna", "radha", "brahma", "laxmi"};

        for (String key : keys) {
            lruEvictionPolicy.keyAccessed(key);
        }

        Assert.assertEquals(keys[0], lruEvictionPolicy.getEvictKey());
    }

    @Test
    public void addKeysAndAccess() {
        String[] keys = new String[] {"shiva", "parvathi", "krishna", "radha", "brahma", "laxmi"};
        for (String key : keys) {
            lruEvictionPolicy.keyAccessed(key);
        }
        lruEvictionPolicy.keyAccessed(keys[0]);
        Assert.assertEquals(keys[1], lruEvictionPolicy.getEvictKey());
    }

    @Test
    public void addKeysAndAccess1() {
        lruEvictionPolicy.keyAccessed("A");
        lruEvictionPolicy.keyAccessed("B");
        lruEvictionPolicy.keyAccessed("C");
        lruEvictionPolicy.keyAccessed("D");
        lruEvictionPolicy.keyAccessed("A");
        lruEvictionPolicy.keyAccessed("B");
        lruEvictionPolicy.keyAccessed("B");
        lruEvictionPolicy.keyAccessed("D");

        Assert.assertEquals("C", lruEvictionPolicy.getEvictKey());
        Assert.assertEquals("A", lruEvictionPolicy.getEvictKey());
        Assert.assertEquals("B", lruEvictionPolicy.getEvictKey());
        Assert.assertEquals("D", lruEvictionPolicy.getEvictKey());
    }
}
