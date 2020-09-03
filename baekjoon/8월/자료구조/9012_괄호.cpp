#include <iostream>
#include <string>
#include <stack>

using namespace std;

int main()
{
    int n;
    cin >> n;
    string str;
    stack<char> s;
    bool VPS = true;
    for (int i = 0; i < n; i++)
    {
        cin >> str;
        VPS = true;
        while (!s.empty())
            s.pop();
        for (int j = 0; j < str.length(); j++)
        {
            if (str[j] == '(')
                s.push('(');
            else
            {
                if (s.empty())
                {
                    VPS = false;
                    break;
                }
                char top = s.top();
                s.pop();
                if (top != '(')
                {
                    VPS = false;
                    break;
                }
            }
        }
        if (!s.empty())
            VPS = false;

        if (VPS)
            cout << "YES\n";
        else
            cout << "NO\n";
    }
    return 0;
}