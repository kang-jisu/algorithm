#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int arr[8][8];
int test[8][8];
int n,m;
int maxCnt;

void bfs(int a,int x,int b,int y,int c,int z){

    queue<pair<int,int>> q;
    for(int i=0; i<n; i++){
        for(int j=0; j<m ;j++){
            test[i][j]=arr[i][j];
            if(arr[i][j]==2)q.push(make_pair(i,j));
        }
    }
    test[a][x]=1;
    test[b][y]=1;
    test[c][z]=1;
        
       while(!q.empty()){
        int x=q.front().first;
        int y=q.front().second;
        test[x][y]=2;
        q.pop();
        if(x-1>=0&&test[x-1][y]==0)q.push(make_pair(x-1,y));
        if(x+1<n&&test[x+1][y]==0)q.push(make_pair(x+1,y));
        if(y-1>=0&&test[x][y-1]==0)q.push(make_pair(x,y-1));
        if(y+1<m&&test[x][y+1]==0)q.push(make_pair(x,y+1));
    }

    int cnt=0;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            if(test[i][j]==0)cnt++;
        }
    }
    if(maxCnt<cnt)maxCnt=cnt;


}
int main(){

     vector< pair<int,int> > v;
    cin>>n>>m;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin>>arr[i][j];
            if(arr[i][j]==0)v.push_back(make_pair(i,j));
        }
     }

     for(int i=0; i<v.size()-2; i++){
         for(int j=i+1; j<v.size()-1;j++){
             for(int k=j+1; k<v.size(); k++){
                 bfs(v[i].first,v[i].second,v[j].first,v[j].second,v[k].first,v[k].second);
             }
         }
     }


    cout<<maxCnt<<"\n";

    return 0;
}