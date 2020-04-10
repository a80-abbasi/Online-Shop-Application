package Model;

public class Comment {
    private Account account;
    private Product product;
    private String comment;
    private CommentStatus status;
    private boolean checkBuy;

    public Comment(Account account, Product product, String comment) {
        this.account = account;
        this.product = product;
        this.comment = comment;
        status = CommentStatus.WAITING_FOR_CONFIRM;
    }

    public Comment() {
        this(null, null, "");
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public CommentStatus getStatus() {
        return status;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }
}
