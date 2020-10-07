#include <iostream>
using namespace std;

int cable[10001];
int result = 0;
void binarySearch(long long left, long long right, int k, int n)
{
    long long mid = (left + right) / 2;
    if (result == mid)
        return;
    long long cnt = 0;
    for (int i = 0; i < k; i++)
    {
        cnt += cable[i] / mid;
    }
    if (cnt < n)
    {
        binarySearch(left, mid, k, n);
    }
    else
    {
        result = mid;
        binarySearch(mid, right, k, n);
    }
}

int main()
{
    int k, n;
    cin >> k >> n;
    long long sum = 0;

    for (int i = 0; i < k; i++)
    {
        cin >> cable[i];
        sum += cable[i];
    }

    long long cnt = 0;
    long long tmpn = n;
    while (1)
    {
        long long tmp = sum / tmpn;
        for (int i = 0; i < k; i++)
        {
            cnt += cable[i] / tmp;
        }
        if (cnt >= n)
        {
            break;
        }
        else
        {
            cnt = 0;
            tmpn++;
        }
    }
    long long left = sum / tmpn;
    if (tmpn == 1)
        cout << left << "\n";
    else
    {
        tmpn--;
        long long right = sum / tmpn;

        binarySearch(left, right, k, n);
        cout << result << "\n";
    }
    return 0;
}