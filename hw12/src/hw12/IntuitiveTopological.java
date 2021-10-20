package hw12;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Topological Sort
 * 
 * @author Somya Khare
 * @version 10/01/21
 */
public class IntuitiveTopological implements TopologicalSort {

	BetterDiGraph diGraph;
    private Iterable<Integer> od;
	
	public IntuitiveTopological(BetterDiGraph graph){
		diGraph = graph;
	}
	private void topOrder(BetterDiGraph betterDiGraph) {
        LinkedList<Integer> topOrder = new LinkedList<Integer>();
        while (betterDiGraph.getVertexCount() > 0) {
            HashMap<Integer, Integer> hashOrder = new HashMap<Integer, Integer>();

            for (Integer i : betterDiGraph.vertices()) {
                hashOrder.put(i, betterDiGraph.getIndegree(i));
            }

            for (Integer i : hashOrder.keySet()) {
                if (hashOrder.get(i) == 0) {
                    topOrder.add(i);
                    betterDiGraph.removeVertex(i);
                }
            }
        }
        od = topOrder;
    }

    @Override
    public Iterable<Integer> order() {
        return od;
    }
    
    /*
	@Override
	public Iterable<Integer> order() {
		LinkedList<Integer> topologicalSort = new LinkedList<>();
		int count = diGraph.getVertexCount();
		if(isDAG()) {
			while(count!=0) {
				for(Object v:diGraph.vertices()) {
					if(diGraph.getIndegree((Integer) v)==0) {
						topologicalSort.add((Integer) v);
						diGraph.removeVertex((Integer)v);
						count++;
					}
				}
			}
		}
		return topologicalSort;
	}
	*/

	@Override
	public boolean isDAG() {
		return !isCyclic();
	}
	
	private boolean isCyclic() {
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		HashMap<Integer, Boolean> reStack = new HashMap<Integer, Boolean>();
		for(Object v: diGraph.vertices()) {
			visited.put((Integer) v, false);
			reStack.put((Integer) v, false);
		}
		
		for(Object v: diGraph.vertices()) {
			if(isCyclicUtil((Integer) v,visited,reStack)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isCyclicUtil(int v, HashMap<Integer, Boolean> visited, HashMap<Integer, Boolean> reStack) {
		if(reStack.get(v))	return true;
		if(visited.get(v)) return false;
		
		visited.put(v,true);
		reStack.put(v, true);
		Iterable<Integer> children = diGraph.getAdj(v);
		for(Integer c: children)
			if(isCyclicUtil(c,visited,reStack))
				return true;
		return false;
	}
	private boolean hasCycle(int node, LinkedList<Integer> visited) {
		if(visited.contains(node)) {
			return true;
		}
		
		visited.add(node);
		for(Object nextNode: diGraph.getAdj(node)){
			if(hasCycle((Integer) nextNode, visited)) {
			return true;
			}
		}
		visited.remove(visited.size()-1);
		return false;
		
	}

}
