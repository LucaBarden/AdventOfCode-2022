#include <bits/stdc++.h>

typedef long long ll;

using namespace std;

ifstream infile("input.txt");

bool isEmpty(string buffer)
{
    char regexNumber[] = "[0-9]+";
    regex pat(regexNumber);
    if (regex_match(buffer, pat))
    {
        return false;
    }
    return true;
}

signed main()
{

    int sum = 0;
    int maxsum = 0;
    string i;
    while (getline(infile, i))
    {
        if (isEmpty(i))
        {
            maxsum = max(maxsum, sum);
            sum = 0;
        }
        else
        {
            sum += stoi(i);
            cout << stoi(i) << endl;
        }
    }
    cout << "Result Part1: " << maxsum << endl;
}
