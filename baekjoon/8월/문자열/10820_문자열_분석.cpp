#include <iostream>
#include <cstring>
using namespace std;

int main()
{
    char str[101];
    int small = 0;
    int big = 0;
    int num = 0;
    int space = 0;

    while (cin.getline(str, 105))
    {
        if (str[0] == '\0')
            break;
        small = 0;
        big = 0;
        num = 0;
        space = 0;
        for (int i = 0; i < strlen(str); i++)
        {
            if (str[i] >= 'a' && str[i] <= 'z')
                small++;
            else if (str[i] >= 'A' && str[i] <= 'Z')
                big++;
            else if ((int)(str[i] - '0') >= 0 && int(str[i] - '0') <= 9)
            {
                num++;
            }
            else
                space++;
        }
        cout << small << " " << big << " " << num << " " << space << "\n";
    }
    return 0;
}