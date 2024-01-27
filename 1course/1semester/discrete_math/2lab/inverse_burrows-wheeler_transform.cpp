#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
void step(vector<vector<char>>& a, const string& s) {
    for (int i = 0; i < s.size(); i++) {
        a[i].insert(a[i].begin(), s[i]);
    }
    sort(a.begin(), a.end());
}
int main() {
    string s;
    cin >> s;
    vector<vector<char>>a(s.size());
    for (int i = 0; i < s.size(); i++) {
        step(a, s);
    }
    string ans = "";
    for (int i = 0; i < s.size(); i++) {
        ans += a[0][i];
    }
    for (int i = 1; i < s.size(); i++) {
        string curr = "";
        for (int j = 0; j < s.size(); j++) {
            curr += a[i][j];
        }
        ans = min(ans, curr);
    }
    cout << ans << endl;
}
