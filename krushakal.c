#include <stdio.h>
#include <stdlib.h>

#define MAX 30

typedef struct {
    int u, v, w;
} Edge;

Edge edges[MAX], result[MAX];
int parent[MAX];

int find(int i) {
    while (parent[i] != i)
        i = parent[i];
    return i;
}

void unionSets(int i, int j) {
    int a = find(i);
    int b = find(j);
    parent[a] = b;
}

int cmp(const void *a, const void *b) {
    return ((Edge *)a)->w - ((Edge *)b)->w;
}

void kruskal(int n, int e) {
    qsort(edges, e, sizeof(Edge), cmp);
    for (int i = 0; i < n; i++)
        parent[i] = i;

    int count = 0, i = 0, total = 0;
    while (count < n - 1 && i < e) {
        Edge curr = edges[i++];
        if (find(curr.u) != find(curr.v)) {
            result[count++] = curr;
            total += curr.w;
            unionSets(curr.u, curr.v);
        }
    }

    printf("Edges in MST:\n");
    for (i = 0; i < count; i++)
        printf("%d - %d: %d\n", result[i].u, result[i].v, result[i].w);
    printf("Total weight: %d\n", total);
}

int main() {
    int n = 4, e = 5;
    edges[0] = (Edge){0, 1, 10};
    edges[1] = (Edge){0, 2, 6};
    edges[2] = (Edge){0, 3, 5};
    edges[3] = (Edge){1, 3, 15};
    edges[4] = (Edge){2, 3, 4};

    kruskal(n, e);
    return 0;
}
