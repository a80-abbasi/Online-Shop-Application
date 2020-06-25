package Model.Product;

import Model.Account.Account;

import java.util.Date;

public class Comment {
    private String accountUserName;
    private String productId;
    private String title;
    private String comment;
    private CommentStatus status;
    private Date date;
    private boolean bought;

    public Comment(Account account, Product product, String comment, String title) {
        this.accountUserName = account.getUsername();
        this.productId = product.getProductId();
        this.comment = comment;
        this.title = title;
        date = new Date();
        status = CommentStatus.WAITING_FOR_CONFIRM;
        Product.getProductByID(productId).addAComment(this);
    }

    public Comment() {

    }

    public Account getAccount() {
        return Account.getAccountByUsername(accountUserName);
    }

    public void setAccountUserName(Account account) {
        this.accountUserName = account.getUsername();
    }

    public Product getProductId() {
        return Product.getProductByID(productId);
    }

    public void setProductId(Product product) {
        this.productId = product.getProductId();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
    }

    public String getAccountUserName() {
        return accountUserName;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "account=" + accountUserName +
                ", product=" + productId +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                ", checkBuy=" + bought +
                '}';
    }
}
