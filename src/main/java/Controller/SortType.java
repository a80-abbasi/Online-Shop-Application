package Controller;

public enum SortType {
    SORT_BY_TIME("Sort By Time"), SORT_BY_SCORE("Sort By Score"), SORT_BY_VISIT("Sort By Visit Number");
    private String sortType;

    SortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortType() {
        return sortType;
    }
}
