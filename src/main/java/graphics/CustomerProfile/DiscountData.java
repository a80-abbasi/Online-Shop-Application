package graphics.CustomerProfile;

public class DiscountData {
    private String discountId;
    private String discountStartTime;
    private String discountEndTime;
    private String percent;
    private String maxPossibleUsage;

    public DiscountData(String discountId, String discountStartTime, String discountEndTime, String percent, String maxPossibleUsage) {
        this.discountId = discountId;
        this.discountStartTime = discountStartTime;
        this.discountEndTime = discountEndTime;
        this.percent = percent;
        this.maxPossibleUsage = maxPossibleUsage;
    }

    public String getDiscountId() {
        return discountId;
    }

    public String getDiscountStartTime() {
        return discountStartTime;
    }

    public String getDiscountEndTime() {
        return discountEndTime;
    }

    public String getPercent() {
        return percent;
    }

    public String getMaxPossibleUsage() {
        return maxPossibleUsage;
    }
}
