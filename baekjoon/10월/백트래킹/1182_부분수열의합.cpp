#include <iostream>
using namespace std;

int n, s;
int A[21];
int result;
void combi(int sum, int i)
{
    if (i == n)
    {
        if (sum == s)
            result++;
    }
    else
    {
        combi(sum + A[i], i + 1);
        combi(sum, i + 1);
    }
}
int main()
{

    cin >> n >> s;
    for (int i = 0; i < n; i++)
    {
        cin >> A[i];
    }
    combi(0, 0);
    if (s == 0)
        result--;
    cout << result << "\n";

    return 0;
}