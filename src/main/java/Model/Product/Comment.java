package Model.Product;

import Model.Account.Account;

import java.util.Date;

public class Comment {
    private Account account;
    private Product product;
    private String title;
    private String comment;
    private CommentStatus status;
    private Date date;
    private boolean bought;

    public Comment(Account account, Product product, String comment, String title) {
        this.account = account;
        this.product = product;
        this.comment = comment;
        this.title = title;
        date = new Date();
        product.addAComment(this);
        status = CommentStatus.WAITING_FOR_CONFIRM;
    }

    public Comment() {
        this(null, null, "", "");
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

    @Override
    public String toString() {
        return "Comment{" +
                "account=" + account +
                ", product=" + product +
                ", title='" + title + '\'' +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                ", checkBuy=" + bought +
                '}';
    }
}
