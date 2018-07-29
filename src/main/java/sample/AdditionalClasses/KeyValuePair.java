package sample.AdditionalClasses;

public class KeyValuePair {

    private Object key;
    private Object value;

    public KeyValuePair(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue(){
        return value;
    }

    @Override
    public String toString() {
        return (String) value;
    }
}