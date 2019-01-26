package org.fenxui.application.view.factory;

import org.fenxui.core.exception.FenxuiInitializationException;

import java.util.*;

public class GraphRunner<T> {
	private final Map<String, T> targetMap;
	private GraphRunCommand<T> command = (noop) -> {};

	public GraphRunner(Map<String, T> targetMap) {
		this.targetMap = targetMap;
	}

	public void setCommand(GraphRunCommand<T> command) {
		this.command = command;
	}

	public RunMap runGraph(ContextGraph contextGraph) throws FenxuiInitializationException {
		RunMap runMap = new RunMap();
		contextGraph.run(targetMap, command, runMap);
		return runMap;
	}

	public void runRemainingNodes(RunMap runMap) throws FenxuiInitializationException {
		runMap.complete(targetMap, command);
	}

	@FunctionalInterface
	public interface GraphRunCommand<T> {
		void execute(T object) throws FenxuiInitializationException;
	}

	public class RunMap {
		private Set<String> processed = new HashSet<>();

		void complete(Map<String,T> targetMap, GraphRunCommand<T> command) throws FenxuiInitializationException {
			for (String s : targetMap.keySet()) {
				if (!processed.contains(s)) {
					T target = targetMap.get(s);
					command.execute(target);
				}
			}
		}

		void addProcessed(String name) {
			processed.add(name);
		}
	}
}
