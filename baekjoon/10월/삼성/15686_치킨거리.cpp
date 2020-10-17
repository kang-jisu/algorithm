#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int city[51][51];
int tmpcity[51][51];

int n, m;
int csize; //chicken house size(num cnt )
int mindis = 2000000000;

vector<pair<int, int>> ch;
int flag[14];

// 지금은 치킨집만 vector에 넣었는데 집도 vector에 넣어서 집 vector 만큼만 for문 돌려서
// 치킨집 조합 선택된경우만 비교해서 계산해도 될듯.
void solve()
{
    int sum = 0;
    int dis = 2000000000;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            if (city[i][j] == 1)
            { // 집에서 치킨집과의 거리 구할거
                dis = 2000000000;
                for (int a = 0; a < csize; a++)
                {
                    if (flag[a] == 1)
                    { // 지금단계에서 선택된 치킨집이면
                        int curdis = abs(i - ch[a].first) + abs(j - ch[a].second);
                        if (curdis < dis)
                        {
                            dis = curdis;
                        }
                    }
                }
                sum += dis;
            }
        }
    }

    if (mindis > sum)
        mindis = sum;
}
int main()
{

    cin >> n >> m;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cin >> city[i][j];
            tmpcity[i][j] = city[i][j];
            if (city[i][j] == 2)
            {
                ch.push_back({i, j});
                csize++;
            }
        }
    }

    // m개만 뽑는 조합 구하기 위한 combi vector
    vector<int> combi(csize);
    for (int i = 0; i < m; i++)
    {
        combi[csize - i - 1] = 1;
    }

    do
    {
        for (int i = 0; i < csize; i++)
        {
            flag[i] = combi[i];
        }
        //flag[i]==1인 (선택된 치킨집) 에서만  치킨거리 구해서 min값 추출
        solve();
    } while (next_permutation(combi.begin(), combi.end()));
    cout << mindis << "\n";
    return 0;
}
