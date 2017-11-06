public class UnionFind {
    // Break it out into smaller pieces
        // what is the problem we are trying to solve - find # of connected components in graph represented by
        // 1 one dimentional array
    // what Data Structure can represent the problem
        // value at each index of the first array viz.,
    // what is the interface ?
        // UnionFind(int N) - initialize N sites with integer names (0 to N-1)
        // int numberOfComponents()
        // int find(int p) - what component # site # p belongs to
        // void union(int p, int q) - add connection between p and q
        // boolean connected(int p, int q) - return true if p and q are in the same component

    private int[] id;

    private int count;

    public UnionFind(int N) {
        count = N;
        id = new int[N];
        // initially, each site is in its own component
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int numberOfComponents() {return count;}

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q){
        int pComponent = find(p);
        int qComponent = find(q);

        if (pComponent == qComponent) {
            return;
        }

        // the opposite could have also been done
        // viz., id[qComponent] = pComponent;
        id[pComponent] = qComponent;

        // dec # of components by 1
        count -= 1;
    }

    public int find(int p) {
        // keep looking for an entry in the 'id' array
        // where index == value[index]
        while (id[p] != p) {
            p = id[p];
        }

        return id[p];
    }

    public static void main(String[] args) {
        // hard coding the inputs.
        // alternatively, we can also read inputs from files - but not now
        int n = 10;
        // how to interpret inputs:
        // pInputs[0] = 4 and qInputs[0] = 3 => 4 is connected to 3
        // pInputs[1] = 3 and qInputs[1] = 8 => 3 is connected to 8
        // and so on...
        int[] pInputs = {4, 3, 6, 9, 2, 8, 5, 7, 6, 1};
        int[] qInputs = {3, 8, 5, 4, 1, 9, 0, 2, 1, 0};

        UnionFind uf = new UnionFind(n);
        for (int i = n-1; i >= 0; i--) {
            if (uf.connected(pInputs[i], qInputs[i])) {
                continue;
            }
            uf.union(pInputs[i], qInputs[i]);
            System.out.println(pInputs[i] + " " + qInputs[i]);
        }

        System.out.println(uf.numberOfComponents() + " components");
    }
}
