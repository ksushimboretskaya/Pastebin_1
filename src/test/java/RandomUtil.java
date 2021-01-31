import java.util.stream.Collectors;
import java.util.Random;

public class RandomUtil {
    static String generateRandomString() {
        String symbols = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "ABCDEFGHIJKLNMOPQRSTUVWXYZ";
        return new Random()
                .ints(10, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

    }
}
