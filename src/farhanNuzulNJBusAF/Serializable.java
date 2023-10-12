package farhanNuzulNJBusAF;

import java.util.HashMap;
//masih banyak yang salah
public class Serializable {
    private static HashMap<Class<?>, Integer> mapCounter = new HashMap<>();
    public int id;

    public Serializable() {
        Class<?> currClass = this.getClass(); //Class<?> currClass p this,getClass() //masih belum paham <?>
        if (mapCounter.containsKey(currClass)) {
            this.id = mapCounter.get(currClass);
            mapCounter.put(currClass, this.id + 1);
        } else {
            this.id = 0;
            mapCounter.put(currClass, this.id + 1);
        }
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
