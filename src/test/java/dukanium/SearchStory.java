package dukanium;

import org.testng.annotations.Test;

/**
 *
 * @author nuboat 
 */
public class SearchStory extends SeleniumStory {
    
    @Override
    public String getStory() {
        return "search.story";
    }
    
    @Test
    public void test() throws Throwable {
		run();
    }

}
