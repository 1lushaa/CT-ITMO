#include <iostream>
#include <vector>
#include <string>
using namespace std;
 
string elCon(int n) {
    string num = to_string(n);
    return "((A" + num + "|" + "B" + num + ")" + "|" + "(A" + num + "|" + "B" + num + "))";
}
 
string elDis(int n) {
    string num = to_string(n);
    return "((A" + num + "|" + "A" + num + ")" + "|" + "(B" + num + "|" + "B" + num + "))";
}
 
string con(const string& ans, const int& n) {
    return "((" + ans + "|" + elDis(n) + ")" + "|" + "(" + ans + "|" + elDis(n) + "))";
}
 
string dis(const string& ans, const int& n) {
    return "((" + ans + "|" + ans + ")" + "|" + "(" + elCon(n) + "|" + elCon(n) + "))";
}
 
string bit(const string& ans, const int& n) {
    return "((" + ans + "|" + elDis(n) + ")|(" + "A" + to_string(n) + "|" + "B" + to_string(n) + "))";
}
 
string shef(const int& n) {
    string ans = elCon(0);
    if (n == 1) {
        return ans;
    }
    else {
        for (int i = 1; i < n; i++) {
            ans = bit(ans, i);
        }
        return ans;
    }
}
 
int main() {
    int n;
    cin >> n;
    cout << shef(n) << endl;
    return 0;
}
