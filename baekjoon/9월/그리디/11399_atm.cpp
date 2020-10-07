#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
    int n;
    cin >> n;

    int time[1000];
    for (int i = 0; i < n; i++)
    {
        cin >> time[i];
    }
    sort(time, time + n);
    int sum = 0;
    int result = 0;
    for (int i = 0; i < n; i++)
    {
        sum += time[i];
        result += sum;
    }
    cout << result << "\n";

    return 0;
}