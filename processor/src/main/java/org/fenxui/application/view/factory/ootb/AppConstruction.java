package org.fenxui.application.view.factory.ootb;

import org.fenxui.application.view.factory.ContextGraph;
import org.fenxui.application.view.factory.GraphRunner;
import org.fenxui.application.view.factory.PagePostProcessorPrep;
import org.fenxui.application.view.factory.handler.FieldPostProcessor;
import org.fenxui.application.view.factory.handler.page.AbstractFrameContext;
import org.fenxui.application.view.factory.handler.page.PageContext;
import org.fenxui.core.exception.FenxuiInitializationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Hold context of pages and menu links for decorator to determine org.fenxui.api.option.layout
 */
public class AppConstruction extends AbstractFrameContext {
	private final ContextGraph graph = new ContextGraph();

	private final Map<String, PagePostProcessorPrep> pagePostProcessors = new HashMap<>();
	private final Map<String, PageContext> pageContextMap = new HashMap<>();

	public AppConstruction() {
		super();
	}

	public void postProcess() throws FenxuiInitializationException {
		for (String pageName: pagePostProcessors.keySet()) {
			PageContext pageContext = pageContextMap.get(pageName);
			PagePostProcessorPrep fieldPostProcessor = pagePostProcessors.get(pageName);
			fieldPostProcessor.prepare(pageContext);
		}

		GraphRunner<PageContext> graphRunner = new GraphRunner(pageContextMap);
		graphRunner.setCommand((PageContext pageContext) -> pageContext.postprocess());
		GraphRunner.RunMap runMap = graphRunner.runGraph(graph);
		graphRunner.runRemainingNodes(runMap);
	}

	@Override
	public void addPageScopedFieldPostProcessor(Class dependentClass, String requiredClassName, FieldPostProcessor fieldPostProcessor) {
		PagePostProcessorPrep pagePostProcessorPrep = pagePostProcessors.get(requiredClassName);
		if (pagePostProcessorPrep == null) {
			pagePostProcessorPrep = new PagePostProcessorPrep();
			pagePostProcessors.put(requiredClassName, pagePostProcessorPrep);
		}
		pagePostProcessorPrep.addFieldPostProcessor(fieldPostProcessor);
		graph.accept(dependentClass.getSimpleName(), requiredClassName);
	}

	@Override
	public void addPageContext(String name, PageContext pageContext) {
		pageContextMap.put(name, pageContext);
	}
}
