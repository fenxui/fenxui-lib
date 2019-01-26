package org.fenxui.application.view.factory;

import org.fenxui.core.exception.FenxuiInitializationException;

import java.util.*;

public class ContextGraph {
	final String name;
	Map<String, GraphNode> graph = new HashMap<>();

	ContextGraph(String name) {
		this.name = name;
	}

	public ContextGraph() {
		this("root");
	}

	public void accept(String dependentName, String requiredName) {
		GraphNode graphNode = find(requiredName);
		if (graphNode == null) {
			graphNode = new GraphNode(requiredName);
			graph.put(requiredName, graphNode);
		}

		GraphNode dependentNode = find(dependentName);
		if (dependentNode == null) {
			dependentNode = new GraphNode(dependentName);
		}

		if (!dependentName.equals(requiredName)) {
			graphNode.addDependent(dependentNode);
			dependentNode.addPrerequisite(graphNode);
		}
	}

	GraphNode find(String requiredName) {
		Iterator<String> topIt = graph.keySet().iterator();
		GraphNode graphNode = graph.get(requiredName);
		while (topIt.hasNext() && graphNode == null) {
			GraphNode temp = graph.get(topIt.next());
			graphNode = temp.find(requiredName);
		}
		return graphNode;
	}

	public <T> void run(Map<String, T> targetMap, GraphRunner.GraphRunCommand<T> command, GraphRunner.RunMap runMap) throws FenxuiInitializationException {
		for (String s: graph.keySet()) {
			GraphNode graphNode = graph.get(s);
			graphNode.run(targetMap, command, runMap);
		}
	}

	private static class GraphNode extends ContextGraph {
		private Set<String> requisiteNodeNames = new HashSet<>();
		private Set<String> satisfiedNodeNames = new HashSet<>();
		private boolean evaluated;

		private GraphNode(String name) {
			super(name);
		}

		public void addDependent(GraphNode graphNode) {
			graph.put(graphNode.name, graphNode);
		}

		public void addPrerequisite(GraphNode graphNode) {
			requisiteNodeNames.add(graphNode.name);
		}

		@Override
		public <T> void run(Map<String, T> targetMap, GraphRunner.GraphRunCommand<T> command, GraphRunner.RunMap runMap) throws FenxuiInitializationException {
			if (satisfiedNodeNames.size() == requisiteNodeNames.size() && !evaluated) {
				T target = targetMap.get(name);
				command.execute(target);
				evaluated = true;
				runMap.addProcessed(name);
				for (String s: graph.keySet()) {
					GraphNode graphNode = graph.get(s);
					graphNode.satisfiedNodeNames.add(name);
					graphNode.run(targetMap, command, runMap);
				}
			}
		}
	}
}
