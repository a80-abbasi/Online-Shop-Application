package Model.Product;

import Model.Account.Customer;

public class Score {
    private Customer customer;
    private Product product;
    private int score;

    public Score(Customer customer, Product product, int score) {
        this.customer = customer;
        this.product = product;
        this.score = score;
    }

    public Score() {
        this(null, null, 0);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "customer=" + customer +
                ", product=" + product +
                ", score=" + score +
                '}';
    }
}
