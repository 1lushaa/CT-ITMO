#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
int main() {
    map<int, string> m1;
    for (int i = 0; i < 26; i++) {
        char ch = 'a' + i;
        string s(1, ch);
        m1.insert(make_pair(i, s));
    }
    int cnt = 26;
    int n;
    cin >> n;
    vector<int>a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    string buffer = m1.find(a[0])->second;
    for (int i = 1; i < n; i++) {
        if (m1.count(a[i])) {
            m1.insert(make_pair(cnt, buffer + m1.find(a[i])->second[0]));
            buffer = m1.find(a[i])->second;
        }
        else {
            buffer += buffer[0];
            m1.insert(make_pair(cnt, buffer));
        }
        cnt++;
    }
    for (int i = 0; i < n; i++) {
        cout << m1.find(a[i])->second;
    }
    cout << endl;
    return 0;
}
