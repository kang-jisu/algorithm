#include <iostream>
using namespace std;

char tree[26][2];
void pre(int node)
{
    cout << (char)(node + 'A');
    if (tree[node][0] != '.')
    {
        // cout << node << "0";
        pre((int)tree[node][0] - 'A');
    }
    if (tree[node][1] != '.')
    {
        // cout << node << "1"/;
        pre((int)tree[node][1] - 'A');
    }
}
void inorder(int node)
{
    if (tree[node][0] != '.')
        inorder((int)tree[node][0] - 'A');
    cout << (char)(node + 'A');
    if (tree[node][1] != '.')
        inorder((int)tree[node][1] - 'A');
}
void post(int node)
{
    if (tree[node][0] != '.')
        post((int)tree[node][0] - 'A');
    if (tree[node][1] != '.')
        post((int)tree[node][1] - 'A');
    cout << (char)(node + 'A');
}
int main()
{

    int n;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        char tmp;
        cin >> tmp >> tree[(int)tmp - 'A'][0] >> tree[(int)tmp - 'A'][1];
    }
    pre(0);
    cout << "\n";
    inorder(0);
    cout << "\n";
    post(0);
    cout << "\n";

    return 0;
}
