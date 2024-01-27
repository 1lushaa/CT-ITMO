#include <iostream>
#include <vector>
#include <string>
#include <cmath>
 
using namespace std;
 
void zhegalkin(string& ans, string& boolvector) {
    if (boolvector.size() == 0) {
        return;
    }
    ans += (char)boolvector[0];
    string curr = "";
    for (int i = 0; i < boolvector.size() - 1; i++) {
        int topush = ((int)boolvector[i] + (int)boolvector[i + 1]) % 2;
        curr.append(to_string(topush));
    }
    zhegalkin(ans, curr);
}
 
int main() {
    int n;
    cin >> n;
    int m = pow(2, n);
    vector<string>str(m);
    string func = "";
    for (int i = 0; i < m; i++) {
        cin >> str[i];
        char c;
        cin >> c;
        func += c;
    }
    string zheg = "";
    zhegalkin(zheg, func);
    for (int i = 0; i < m; i++) {
        cout << str[i] << " " << zheg[i] << endl;
    }
    return 0;
}
