#include <iostream>
#include <stack>
#include <algorithm>
using namespace std;

int n;
int A[12];
int P[11];
int idx = 0;
int minVal = 1000000000;
int maxVal = -1000000000;
int oper(int a, int b, int o)
{
    if (o == 0)
        return a + b;
    else if (o == 1)
        return a - b;
    else if (o == 2)
        return a * b;
    else
        return a / b;
}
void cal()
{
    int i = 1; // A idx
    int j = 0; // P idx
    int a = A[0];
    for (i = 1; i < n; i++)
    {
        a = oper(a, A[i], P[j++]);
    }
    if (minVal > a)
        minVal = a;
    if (maxVal < a)
        maxVal = a;
}
int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> A[i];
    }
    for (int i = 0; i < 4; i++)
    {
        int tmp;
        cin >> tmp;
        for (int j = 0; j < tmp; j++)
        {
            P[idx++] = i;
        }
    }
    do
    {
        cal();

    } while (next_permutation(P, P + n - 1));
    cout << maxVal << "\n";
    cout << minVal << "\n";
    return 0;
}