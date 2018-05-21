import java.security.SecureRandom;
import java.time.LocalTime;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.logging.Logger;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.pow;
import static java.time.LocalTime.now;

/**
 * Note: it's up to you to square f and provide both partial derivatives for f^2 (for x and y).
 */
@SuppressWarnings("unused")
public final class SteepestDecent {

    private static final double MAX_ERROR = 0.01;
    private static final double LEARNING_RATE = 0.01;
    private static final Logger log = Logger.getAnonymousLogger();
    private static final Random RANDOM = new SecureRandom();
    private static final int MAX_RAND_VAR = 5;
    private final BinaryOperator<Double> partDerivativeX, partDerivativeY;
    private double x, y;

    @SuppressWarnings({"ConstantConditions", "MethodParameterNamingConvention", "ConstantAssertCondition"})
    public SteepestDecent(final BinaryOperator<Double> partialDerivativeX, final BinaryOperator<Double> partialDerivativeY) {
        this(partialDerivativeX, partialDerivativeY, RANDOM.nextInt(MAX_RAND_VAR), RANDOM.nextInt(MAX_RAND_VAR));
    }

    @SuppressWarnings({"ConstantConditions", "MethodParameterNamingConvention", "ConstantAssertCondition", "WeakerAccess"})
    public SteepestDecent(final BinaryOperator<Double> partialDerivativeX, final BinaryOperator<Double> partialDerivativeY, final double x, final double y) {
        assert LEARNING_RATE > 0;
        assert MAX_ERROR >= 0;
        partDerivativeX = partialDerivativeX;
        partDerivativeY = partialDerivativeY;
        this.x = x;
        this.y = y;
    }

    public static void main(final String... args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");
        final SteepestDecent algo = new SteepestDecent((x, y) -> ((-4.0) * x * y) + (4.0 * pow(x, 3.0)), (x, y) -> (2 * y) - (2 * pow(x, 2.0)), -6, 5);
        algo.train();
    }

    @SuppressWarnings("MethodCallInLoopCondition")
    public void train() {
        final LocalTime start = now();
        int steps = 0;
        final double initX = x;
        final double initY = y;
        double errX, errY, deltaX, deltaY;

        do {
            steps++;
            deltaX = partDerivativeX.apply(x, y);
            deltaY = partDerivativeY.apply(x, y);
            log.info(String.format("delta x = %f%ndelta y = %f%n", deltaX, deltaY));
            x -= (deltaX * LEARNING_RATE);
            y -= (deltaY * LEARNING_RATE);
            log.info(String.format("x = %f%ny = %f%n", x, y));
            // errX = abs(abs(x) - abs(deltaX));
            // errY = abs(abs(y) - abs(deltaY));
            // log.info(String.format("error x = %f error y = %f", errX, errY));
        } while ((abs(deltaX) > MAX_ERROR) || (abs(deltaY) > MAX_ERROR));

        final String line = "--------------------------------------";
        log.info(String.format("Took%n%s%n%d seconds%n%d steps%n%n", line, now().minusSeconds(start.getSecond())
                                                        .getSecond(), steps));
        log.info(String.format("Initial%n%s%nx = %f%ny = %f%n%n", line, initX, initY));
        log.info(String.format("Final%n%s%nx = %f%ny = %f", line, x, y));
    }
}
