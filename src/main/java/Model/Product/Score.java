package Model.Product;

import Model.Account.Customer;

public class Score {
    private Customer customer;
    private Product product;
    private ScoreEnumeration score;

    public Score(Customer customer, Product product, ScoreEnumeration score) {
        this.customer = customer;
        this.product = product;
        this.score = score;
    }

    public Score() {
        this(null, null, null);
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

    public ScoreEnumeration getScore() {
        return score;
    }

    public void setScore(ScoreEnumeration score) {
        this.score = score;
    }
}
