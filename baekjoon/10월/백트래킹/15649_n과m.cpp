#include <iostream>
using namespace std;

int A[9];
int C[9];
int n, m;

void choice( int cnt)
{
    if (cnt == m)
    {
        for (int j = 0; j < m; j++)
        {
            cout<<A[j]<<" ";
        }
        cout << "\n";
    }
    else
    {
        for (int j = 1; j <= n; j++)
        {
            if ( C[j] == 0)
            {
                C[j]=1;
                A[cnt]=j;
                choice(cnt + 1);
                C[j]=0;
            }
        }
    }
}

int main()
{
    cin >> n >> m;
    
    choice(0);
    return 0;
}