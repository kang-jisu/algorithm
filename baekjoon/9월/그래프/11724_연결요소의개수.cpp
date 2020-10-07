#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> graph;
int visit[1001];

void dfs(int v){
    visit[v]=1;
    for(int i=0; i<graph[v].size(); i++){
        if(visit[graph[v][i]]==0){
            dfs(graph[v][i]);
        }
    }
}

int main()
{
    int n, m;   
    cin>>n>>m;
    graph.resize(n+1);
    for(int i=0; i<m; i++){
        int a, b;
        cin>>a>>b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    int count =0;
    for(int i=1; i<=n; i++){
        if(visit[i]==0){
            count++;
            dfs(i);
        }
    }
    cout<<count<<"\n";
    return 0;
}