#include <iostream>
#include <vector>
#include <list>
using namespace std;
int main() {
    list<char>alphabet;
    for (int i = 0; i < 26; i++) {
        alphabet.push_back('a' + i);
    }
    string s;
    cin >> s;
    for (int i = 0; i < s.size(); i++) {
        list<char>::iterator it = alphabet.begin();
        int cnt = 0;
        while (*it != s[i]) {
            cnt++;
            it++;
        }
        cout << cnt + 1 << " ";
        alphabet.erase(it);
        alphabet.insert(alphabet.begin(), s[i]);
    }
    cout << endl;
    return 0;
}
