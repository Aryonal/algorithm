package com.aryon.realize;

import com.aryon.prototype.AbstractGraph;

/**
 * Created by Dai on 2016/12/9.
 */
public class Cycle {
    private boolean[]   marked;
    private boolean[]   color;
    private boolean     hasCycle;
    private boolean     twoColorable = true;
    public Cycle(AbstractGraph g) {
        marked = new boolean[g.v()];
        color = new boolean[g.v()];
        for (int i = 0; i < g.v(); i++)
            if (!marked[i]) dfs(g,i,i);
    }
    
    /**
     * 无环图任两节点间只有一条路径
     * 任一节点只可能被遍历到度数次
     * 任一节点只有一个父节点，只能被父节点标记
     * 无环图的遍历，dfs与bfs轨迹相同，遍历节点顺序不同
     *
     * 无环图一定可双色
     * 可双色等价于不含奇环（证明）
     *
     * @param g graph
     * @param v 当前节点
     * @param u 当前父节点
     */
    private void dfs(AbstractGraph g, int v, int u) {
        if (hasCycle && !twoColorable) return; //there would be StackOverFlow Exception without this.
        marked[v] = true;
        if (g.adj(v) == null) return;
        for (int i : g.adj(v))
            if (!marked[i]) {
                color[i] = !color[v];
                dfs(g,i,v);
            }
            else {
                if (i != u) hasCycle = true;
                if (color[i] == color[v]) twoColorable = false;
            }
    }
        
    public boolean hasCycle() {
        return hasCycle;
    }
    
    public boolean twoColorable() {
        return twoColorable;
    }
    
}
