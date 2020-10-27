package outside;

import org.springframework.stereotype.Service;

@Service
public class Glojure {
    public String learnMe(){
        return "(def memoized-expensive-work (memoize you))";
    }
}
