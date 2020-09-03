#include <iostream>
#include <list>
using namespace std;

int main()
{

    int m;
    string s;
    cin >> s;
    cin >> m;

    list<char> editor(s.begin(), s.end());
    auto cursor = editor.end();
    while (m--)
    {
        char cmd;
        cin >> cmd;
        switch (cmd)
        {
        case 'P':
        {
            char t;
            cin >> t;
            editor.insert(cursor, t);
            break;
        }
        case 'L':
        {
            if (cursor != editor.begin())
                cursor--;
            break;
        }
        case 'D':
        {
            if (cursor != editor.end())
                cursor++;
            break;
        }
        case 'B':
        {
            if (cursor != editor.begin())
            {
                cursor--;
                cursor = editor.erase(cursor);
            }
            break;
        }
        default:
            break;
        }
    }
    for (auto i = editor.begin(); i != editor.end(); i++)
    {
        cout << *i;
    }
    cout << "\n";

    return 0;
}