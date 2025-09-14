package hexlet.code.utils;

public class NamedRoutes {
    public static String getRoot() {
        return "/";
    }

    public static String getUrls() {
        return "/urls";
    }

    public static String getUrl(String id) {
        return "/urls/" + id;
    }

    public static String getUrl(Long id) {
        return getUrl(String.valueOf(id));
    }

    public static String getUrlChecks(String id) {
        return getUrl(id) + "/checks";
    }
    public static String getUrlChecks(Long id) {
        return getUrlChecks(String.valueOf(id));
    }
}

