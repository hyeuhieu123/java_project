package ra.edu.utils;

public class TableConfig {
    private final String tableName;
    private final int itemPerPage;
    private final String countColumn;

    public TableConfig(String tableName, int itemPerPage, String countColumn) {
        this.tableName = tableName;
        this.itemPerPage = itemPerPage;
        this.countColumn = countColumn;
    }

    public String getTableName() { return tableName; }
    public int getItemPerPage() { return itemPerPage; }
    public String getCountColumn() { return countColumn; }
}