package Controller;

public enum FilteringType {
    CATEGORY_FILTER("Category Filter"), NAME_FILTER("Name Filter"),
    EXISTENCE_FILTER("Existence Filter");
    private String filterType;

    FilteringType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterType() {
        return filterType;
    }
}
