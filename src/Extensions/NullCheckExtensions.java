package Extensions;

public final class NullCheckExtensions {

    private NullCheckExtensions() {
    }

    public static <T> T isValidClass(T obj) {
        return isValidClass(obj, "Object");
    }

    public static <T> T isValidClass(T obj, String paramName) {
        if (obj == null) {
            throw new IllegalArgumentException("Error: " + paramName + " cannot be null!");
        }
        return obj;
    }

    public static String isValidString(String str) {
        return isValidClass(str, "Text");
    }

    public static String isValidString(String str, String paramName) {
        if (str == null || str.isBlank()) {
            throw new IllegalArgumentException("Error: " + paramName + " cannot be null or empty!");
        }
        return str;
    }

    public static int isValidId(int id) {
        return isValidId(id, "Id");
    }

    public static int isValidId(int id, String paramName) {
        if (id <= 0) {
            throw new IllegalArgumentException("Error: " + paramName + " must be greater than 0!");
        }
        return id;
    }
}
