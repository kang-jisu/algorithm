#include <iostream>
#include <vector>
using namespace std;

int main()
{

    int n, k;
    cin >> n >> k;
    vector<int> v;
    for (int i = 1; i <= n; i++)
    {
        v.push_back(i);
    }
    cout << "<";
    int idx = -1;
    int copyn = n;
    while (v.size() > 0)
    {
        idx += k;
            idx %= copyn;
        cout << v[idx];
        v.erase(v.begin() + idx);
        idx--;
        copyn--;
        if(v.size()==0)cout<<">";
        else cout<<", ";
    }

    return 0;
}