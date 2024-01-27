#include <iostream>
#include <tuple>
#include <vector>
#include <map>
using namespace std;
int main() {
    int cnt = 26;
    map<string, int>m;
    for (int i = 0; i < 26; i++) {
        char ch = 'a' + i;
        string k(1, ch);
        m.insert(make_pair(k, i));
    }
    string s;
    cin >> s;
    string buffer = "";
    for (int i = 0; i < s.size(); i++) {
        string curr = buffer + s[i];
        if (m.count(curr)) {
            buffer = curr;
        }
        else {
            cout << m.find(buffer)->second << " ";
            m.insert(make_pair(curr, cnt));
            cnt++;
            buffer = s[i];
        }
    }
    cout << m.find(buffer)->second << " ";
}
