package dukanium;

import com.google.common.util.concurrent.MoreExecutors;
import dukanium.step.SeleniumStep;
import static java.util.Arrays.asList;
import java.util.LinkedList;
import java.util.List;
import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.Embedder;
import static org.jbehave.core.reporters.Format.*;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.SilentStepMonitor;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.DefaultWebDriverProvider;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.PerStoriesWebDriverSteps;
import org.jbehave.web.selenium.PropertyWebDriverProvider;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContext;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.jbehave.web.selenium.WebDriverSteps;

/**
 *
 * @author nuboat 
 */
public abstract class SeleniumStory extends ConfigurableEmbedder {

	private final WebDriverProvider webDriverProvider = new DefaultWebDriverProvider();
	private final WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(webDriverProvider);
	private final SeleniumContext context = new SeleniumContext();
	private final ContextView contextView = new LocalFrameContextView().sized(500, 100);
    
    public SeleniumStory() {
        webDriverProvider.initialize();
        configuredEmbedder().useExecutorService(MoreExecutors.newDirectExecutorService());
	}
    
    public abstract String getStory();

	@Override
	public Configuration configuration() {
		return new SeleniumConfiguration()
				.useSeleniumContext(context)
				.useWebDriverProvider(webDriverProvider)
				.useStepMonitor(new SeleniumStepMonitor(contextView, context, new SilentStepMonitor()))
                .useParameterControls(new ParameterControls().useDelimiterNamedParameters(true))
				.useStoryReporterBuilder(new StoryReporterBuilder()
                                            .withDefaultFormats()
                                            .withFormats(CONSOLE, HTML));
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
        final List<Object> steps = new LinkedList<>();
        steps.add(new SeleniumStep(webDriverProvider.get()));
        steps.add(lifecycleSteps);
        steps.add(new WebDriverScreenshotOnFailure(webDriverProvider, configuration().storyReporterBuilder()));

		return new InstanceStepsFactory(configuration(), steps);
	}
    
    @Override
    public void run() throws Throwable {
		configuredEmbedder().runStoriesAsPaths(asList(getStory()));
    }

	public static class SameThreadEmbedder extends Embedder {
		public SameThreadEmbedder() {
			useExecutorService(MoreExecutors.newDirectExecutorService());
		}
	}
}
