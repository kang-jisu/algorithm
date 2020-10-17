#include <iostream>
#include <cmath>
using namespace std;

int col[16];
int n;
int result;
bool isOk(int c)
{
    for (int i = 0; i < c; i++)
    {
        if (col[i] == col[c])
        {
            return false;
        }
        if (abs(col[i] - col[c]) == abs(i - c))
        {
            return false;
        }
    }
    return true;
}
void queen(int cnt)
{
    if (cnt == n)
        result++;
    else
    {
        for (int i = 0; i < n; i++)
        {
            col[cnt] = i;
            if (isOk(cnt))
            {
                queen(cnt + 1);
            }
            col[cnt] = 0;
        }
    }
}
int main()
{
    cin >> n;
    queen(0);
    cout << result << "\n";
    return 0;
}