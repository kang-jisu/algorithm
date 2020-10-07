#include <iostream>
#include <vector>
using namespace std;

int main()
{
    int t;
    cin>>t;
    vector<int> v;
    int visit[1001];
    while(t--){
        int n;
        cin>>n;
        v.clear();
        v.resize(n+1);
        for(int i=0; i<=n; i++){
            visit[i]=0;
        }
        for(int i=1; i<=n; i++){
            cin>>v[i];
        }
        int count = 0;
        for(int i=1; i<=n; i++){
            if(visit[i]==0){
                count ++ ;
                int next = v[i];
                visit[i]=count;
                while(visit[next]==0){
                    visit[next]=count;
                    next = v[next];
                }
            }
        }
        cout<<count<<"\n";

    }
    return 0;
}