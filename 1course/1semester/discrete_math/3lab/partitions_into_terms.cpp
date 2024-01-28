#include <iostream>
#include <vector>
using namespace std;
vector<int>a;
void gen(const int& n, const int& sum, const int& num) {
    if (n == sum) {
        for (int i = 0; i < a.size() - 1; i++) {
            cout << a[i] << "+";
        }
        cout << a[a.size() - 1] << endl;
        return;
    }
    for (int i = num; i <= n; i++) {
        if (sum + i <= n) {
            a.push_back(i);
            gen(n, sum + i, i);
            a.pop_back();
        }
        else {
            break;
        }
    }
}
int main() {
    int n;
    cin >> n;
    gen(n, 0, 1);
}
