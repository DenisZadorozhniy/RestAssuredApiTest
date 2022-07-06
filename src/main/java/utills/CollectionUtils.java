package utills;

import java.util.List;
import java.util.stream.Collectors;

public class CollectionUtils {
    public static <T> boolean isListSorted(List<T> list) {
        return list.stream().sorted().collect(Collectors.toList()).equals(list);
    }
}
