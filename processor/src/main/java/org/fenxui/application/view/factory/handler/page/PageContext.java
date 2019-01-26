package org.fenxui.application.view.factory.handler.page;

import org.fenxui.application.view.components.option.FieldOption;
import org.fenxui.application.view.factory.ContextGraph;
import org.fenxui.application.view.factory.GraphRunner;
import org.fenxui.application.view.factory.handler.FieldPostProcessor;
import org.fenxui.application.view.components.option.MethodOption;
import org.fenxui.application.view.factory.ootb.FrameContext;
import org.fenxui.core.exception.FenxuiInitializationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageContext extends AbstractFrameContext implements FrameContext {

	private final Object applicationPage;
	private final FrameContext frameContext;

	private List<FieldOption> fieldOptions = new ArrayList<>();
	private List<MethodOption> methodOptions = new ArrayList<>();
	private final Map<String, FieldOption> fieldContextMap = new HashMap<>();
	private ContextGraph contextGraph = new ContextGraph();

	public PageContext(Object applicationPage, FrameContext frameContext) {
		super();
		this.applicationPage = applicationPage;
		this.frameContext = frameContext;
		frameContext.addPageContext(applicationPage.getClass().getSimpleName(), this);
	}

	public FrameContext getFrameContext() {
		return frameContext;
	}

	public Object getApplicationPage() {
		return applicationPage;
	}

	public void addPostProcessor(String dependentFieldName, String requiredFieldName, FieldPostProcessor fieldPostProcessor) {
		fieldContextMap.get(requiredFieldName).addPostprocessor(fieldPostProcessor);
		contextGraph.accept(dependentFieldName, requiredFieldName);
	}

	public void postprocess() throws FenxuiInitializationException {
		GraphRunner<FieldOption> graphRunner = new GraphRunner<>(fieldContextMap);
		graphRunner.setCommand(fieldOption-> fieldOption.postprocess());
		graphRunner.runGraph(contextGraph);
	}

	public List<FieldOption> getFieldOptions() {
		return fieldOptions;
	}
	
	public void addFieldOption(FieldOption option) {
		fieldOptions.add(option);
		fieldContextMap.put(option.getFieldName(), option);
	}

	public List<MethodOption> getMethodOptions() {
		return methodOptions;
	}

	public void addMethodOption(MethodOption option) {
		methodOptions.add(option);
	}

	public void addPageContext(String name, PageContext pageContext) {
		frameContext.addPageContext(name, pageContext);
	}

	@Override
	public void addPageScopedFieldPostProcessor(Class klass, String requiredClassName, FieldPostProcessor fieldPostProcessor) {
		frameContext.addPageScopedFieldPostProcessor(klass, requiredClassName, fieldPostProcessor);
	}

}
