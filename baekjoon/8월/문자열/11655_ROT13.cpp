#include <iostream>
#include <string>
using namespace std;

int main()
{
    char str[101];
    cin.getline(str, 101);

    for (int i = 0; str[i] != '\0'; i++)
    {
        char t = str[i];
        if (t >= 65 && t <= 90)
        {
            cout << (char)('A' + (t - 'A' + 13) % 26);
        }
        else if (t >= 97 && t <= 122)
        {
            cout << (char)('a' + (t - 'a' + 13) % 26);
        }
        else
            cout << t;
    }

    return 0;
}