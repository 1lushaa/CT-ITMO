#include <iostream>
#include <vector>
using namespace std;
string gen(string s) {
    int bal = 0;
    for (int i = s.size() - 1; i >= 0; i--) {
        if (s[i] == ')') {
            bal++;
        }
        else {
            bal--;
        }
        if (s[i] == '(' && bal > 0) {
            s[i] = ')';
            int num = (s.size() - i - bal - 2) / 2;
            for (int j = i + 1; j <= i + num + 1; j++) {
                s[j] = '(';
            }
            for (int j = num + i + 2; j < s.size(); j++) {
                s[j] = ')';
            }
            return s;
        }
    }
    return "-";
}
int main() {
    string s;
    cin >> s;
    cout << gen(s) << endl;
}
