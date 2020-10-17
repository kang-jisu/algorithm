#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

// n, m 크기가 달라질때 증가는하지만 줄어들게하지않기.
// 100, 0, -1 테스트케이스 while문 조심

int A[101][101]; // ori
int T[101][101]; // tmp
int r, c, k;
int n, m;
int cnt = 0;

vector<pair<int, int>> srt;

bool comp(pair<int, int> a, pair<int, int> b)
{
    if (a.second == b.second)
    {
        return a.first < b.first;
    }
    else
        return a.second < b.second;
}
void step()
{
    cnt++;

    int maxSize = 0;
    int jdx = 1;
    if (n >= m)
    {
        maxSize = m;
        // R연산
        for (int i = 1; i <= n; i++)
        {
            srt.clear();   // 무슨 수가 몇개있는지 저장할용
            vector<int> v; // 행 정렬용
            jdx = 1;
            for (int j = 1; j <= m; j++)
            {
                if (A[i][j] != 0)
                    v.push_back(A[i][j]);
            }
            sort(v.begin(), v.end());

            int ncnt = 1;
            if (v.size() == 1)
                srt.push_back({v[0], 1});
            for (int j = 1; j < v.size(); j++)
            {
                if (v[j] == v[j - 1])
                {
                    ncnt++;
                }
                else
                {
                    srt.push_back({v[j - 1], ncnt});
                    ncnt = 1;
                }
                if (j == v.size() - 1)
                    srt.push_back({v[j], ncnt});
            }

            sort(srt.begin(), srt.end(), comp);

            for (int j = 0; j < srt.size(); j++)
            {
                T[i][jdx++] = srt[j].first;
                if (jdx > 100)
                    break;
                T[i][jdx++] = srt[j].second;
                if (jdx > 100)
                    break;
            }
            if (srt.size() * 2 > maxSize)
                maxSize = srt.size() * 2;
        }
        m = maxSize;
    }
    else
    {
        maxSize = n;
        // C연산
        for (int i = 1; i <= m; i++)
        {
            srt.clear();   // 무슨 수가 몇개있는지 저장할용
            vector<int> v; // 행 정렬용
            jdx = 1;
            for (int j = 1; j <= n; j++)
            {
                if (A[j][i] != 0)
                    v.push_back(A[j][i]);
            }
            sort(v.begin(), v.end());

            int ncnt = 1;
            if (v.size() == 1)
                srt.push_back({v[0], 1});
            for (int j = 1; j < v.size(); j++)
            {
                if (v[j] == v[j - 1])
                {
                    ncnt++;
                }
                else
                {
                    srt.push_back({v[j - 1], ncnt});
                    ncnt = 1;
                }
                if (j == v.size() - 1)
                    srt.push_back({v[j], ncnt});
            }

            sort(srt.begin(), srt.end(), comp);

            for (int j = 0; j < srt.size(); j++)
            {
                T[jdx++][i] = srt[j].first;
                if (jdx > 100)
                    break;
                T[jdx++][i] = srt[j].second;
                if (jdx > 100)
                    break;
            }
            if (srt.size() * 2 > maxSize)
                maxSize = srt.size() * 2;

            n = maxSize;
        }
    }
}
int main()
{

    cin >> r >> c >> k;
    n = 3;
    m = 3;
    for (int i = 1; i <= 3; i++)
    {
        for (int j = 1; j <= 3; j++)
        {
            cin >> A[i][j];
            T[i][j] = A[i][j];
        }
    }

    while (T[r][c] != k && cnt <= 100)
    {
        for (int i = 0; i <= 100; i++)
        {
            for (int j = 0; j <= 100; j++)
            {
                A[i][j] = T[i][j];
                T[i][j] = 0;
            }
        }
        step();
    }
    if (cnt > 100)
        cout << "-1\n";
    else
        cout << cnt << "\n";
    return 0;
}