import Model.Customer;
import Model.Product;
import Model.Score;
import Model.ScoreEnumeration;
import org.junit.Assert;
import org.junit.Test;

public class ScoreTest {

    @Test
    public void scoreTest () {
        Score score = new Score(new Customer(), new Product(), ScoreEnumeration.GOOD);

        System.out.println(score.getScore());
    }
}
