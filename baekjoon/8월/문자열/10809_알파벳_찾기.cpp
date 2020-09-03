#include <iostream>
#include <string>
using namespace std;

int main()
{
    string word;
    cin >> word;
    int arr[26] = {
        0,
    };
    for (int i = 0; i < 26; i++)
        arr[i] = -1;
    for (int i = 0; i < word.length(); i++)
    {
        if (arr[word[i] - 'a'] != -1)
            continue;
        else
            arr[word[i] - 'a'] = i;
    }
    for (int i = 0; i < 26; i++)
        cout << arr[i] << " ";
    cout << "\n";
    return 0;
}