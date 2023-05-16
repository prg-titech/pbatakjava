package org.batakjava;

import org.extendj.ast.Graph;

public class DependencyChecker {

  public static void main(String[] args) {
    Graph g = new Graph(4);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    g.addEdge(3, 2);
    g.addEdge(3, 3);
    // g.printGraph();

    g.computeComponents();
    System.out.println(g.components);
  }

}
