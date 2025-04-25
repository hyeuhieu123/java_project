package ra.edu.utils;

import java.util.List;
import java.util.Map;

public class TableConfig<T> {
    private  List<T> items;
    private  int totalPages;
    private  Map<T,Integer>itemsMap;
    public TableConfig() {
    }

    public TableConfig(List<T> items, int totalPages) {
        this.items = items;
        this.totalPages = totalPages;
    }
    public TableConfig(Map<T,Integer> itemsMap, int totalPages) {
        this.itemsMap = itemsMap;
        this.totalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public Map<T,Integer> getItemsMap() {
        return itemsMap;
    }

    public int getTotalPages() {
        return totalPages;
    }

}