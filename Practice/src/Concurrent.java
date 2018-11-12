import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Concurrent {
    static Map<String, String> map1 = new ConcurrentHashMap<>();
    public static void main (String args[]) {
        map1.put("k1", "v1");
        map1.put("k2", "v2");
        map1.put("k3", "v3");
        map1.put("k4", "v4");
        map1.put("k5", "v5");
        int count=0;
        for (Iterator iterator = map1.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            System.out.println(key +" -> "+map1.get(key));
            map1.put("k6", "v6"+count++);
            System.out.println("k6" +" -> "+map1.get("k6"));
            System.out.println("-----------");
        }
    }

}
