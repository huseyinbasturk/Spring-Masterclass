package academy.learnprogramming;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Slf4j
@Getter
@Component
public class GameImpl implements Game{

    //==constants==

    //==fields==
    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;
    private final int guessCount;
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }
    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Setter
    private int guess;


    // ==init ==
    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber();
        guess = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("the number is {}", number);
    }
    @PreDestroy
    public void preDestroy(){
        log.info("in Game preDestroy{}");
    }
    // == public methods ==
//    public void setNumberGenerator(NumberGenerator numberGenerator){
//        this.numberGenerator = numberGenerator;
//    }


    @Override
    public void check() {
        checkValidNumberRange();
        if(validNumberRange){
            if(guess > number){
                biggest = guess -1;
            }
            if(guess < number){
                smallest = guess + 1;
            }
        }
        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <=0;
    }

    // == private methods ==
    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
