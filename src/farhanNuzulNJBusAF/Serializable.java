package farhanNuzulNJBusAF;

import java.util.HashMap;

public class Serializable {
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
    public int id;

    protected Serializable(int id) {
        Class<?> currClass = this.getClass();
        if (id >= 0) {
            this.id = id;
            mapCounter.put(currClass, id);
            id++;
        } else{
            this.id = 0;
            mapCounter.put(currClass, id);
            id++;
        }
//        Class<?> currClass = this.getClass();
//        if (mapCounter.containsKey(currClass)) {
//            this.id = mapCounter.get(currClass);
//            mapCounter.put(currClass, id + 1);
//        } else {
//            this.id = 0;
//            mapCounter.put(currClass, id);
//        }
    }

    public int setLastAssignedId(Class<?> currClass, int newId) {
        id = newId;
        mapCounter.put(currClass, newId);
        return newId;
    }

    public int getLastAssignedId(Class<?> currClass) {
        return mapCounter.getOrDefault(currClass, 0);
    }

    public int compareTo(Serializable idParam) {
        return Integer.compare(id, idParam.id);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Serializable) {
            return id == ((Serializable) obj).id;
        }
        return false;
    }

    public boolean equals(Serializable idParam) {
        return id == idParam.id;
    }
}
