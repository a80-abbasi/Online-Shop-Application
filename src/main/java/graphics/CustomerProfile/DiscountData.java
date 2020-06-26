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
}
