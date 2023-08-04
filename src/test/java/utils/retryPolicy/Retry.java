package utils.retryPolicy;
import java.time.Duration;
import java.util.NoSuchElementException;
import net.jodah.failsafe.RetryPolicy;

public class Retry {
    private static final int MAX_ATTEMPTS = 3;
    private static final int WAIT_DURATION_MIN = 1;
  
    public static RetryPolicy<Object> retryPolicy = new RetryPolicy<>()
            .handle(NullPointerException.class)
            .handle(NoSuchElementException.class)
            .handle(AssertionError.class)
            .withDelay(Duration.ofMinutes(WAIT_DURATION_MIN))
            .withMaxRetries(MAX_ATTEMPTS);           
    //Failsafe.with(retryPolicy).run(() -> {});

}

