#include <iostream>
#include <vector>
#include <string>
#include <cmath>
 
using namespace std;
 
string toBinary(int n)
{
    string ans = "";
    while (n > 0) {
        ans += (char)(n % 2);
        n = n / 2;
    }
    return ans;
}
 
bool ifSave0(string& boolvector) {
    if (boolvector[0] == '0') {
        return true;
    }
    return false;
}
 
bool ifSave1(string& boolvector) {
    if (boolvector[boolvector.size() - 1] == '1') {
        return true;
    }
    return false;
}
 
bool ifSamo(string& boolvector) {
    for (int i = 0; i < boolvector.size(); i++) {
        if (boolvector[i] == boolvector[boolvector.size() - 1 - i]) {
            return false;
        }
    }
    return true;
}
 
bool ifMono(string& boolvector) {
    for (int i = 0; i < boolvector.size(); i++) {
        for (int j = 0; j < boolvector.size(); j++) {
            int n = i;
            int m = j;
            bool flag = true;
            while (n > 0 || m > 0) {
                if (n % 2 < m % 2) {
                    flag = false;
                }
                n /= 2;
                m /= 2;
            }
            if ((int)boolvector[i] < (int)boolvector[j] && flag) {
                return false;
            }
        }
    }
    return true;
}
 
bool ifLinear(string& func) {
    vector<int>xors(func.size());
    for (int i = 0; i < func.size(); ++i) {
        xors[i] = (int)func[0];
        for (int j = i; j > 0; j = (j - 1) & i) {
            xors[i] ^= (int)func[j];
        }
        int step = log2(i);
        if (xors[i] == 1 && (i != pow(2, step)) && i <= 32) {
            return false;
        }
    }
    return true;
}
 
int main() {
    int n;
    cin >> n;
    vector<vector<bool>>posts(n, vector<bool>(5));
    for (int i = 0; i < n; i++) {
        int m;
        cin >> m;
        string s;
        cin >> s;
        posts[i][0] = ifSave0(s);
        posts[i][1] = ifSave1(s);
        posts[i][2] = ifSamo(s);
        posts[i][3] = ifMono(s);
        posts[i][4] = ifLinear(s);
    }
    for (int i = 0; i < 5; i++) {
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            if (posts[j][i] == true) {
                cnt++;
            }
        }
        if (cnt == n) {
            cout << "NO" << endl;
            return 0;
        }
    }
    cout << "YES" << endl;
}
