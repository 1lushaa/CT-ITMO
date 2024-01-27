#include <iostream>
#include <vector>
using namespace std;
vector<vector<int>>permutatons;
vector<int> a;
void gen_permutation(const int& n) {
    if (a.size() == n) {
        permutatons.push_back(a);
        return;
    }
    for (int i = 0; i <= 2; i++) {
        a.push_back(i);
        gen_permutation(n);
        a.pop_back();
    }
}
void get_shift(vector<int>& a) {
    for (int i = 0; i < a.size(); i++) {
        if (a[i] == 0) {
            a[i] = 1;
        }
        else if (a[i] == 1) {
            a[i] = 2;
        }
        else {
            a[i] = 0;
        }
        cout << a[i];
    }
    cout << endl;
}
void anti_gray(const int& n) {
    gen_permutation(n);
    for (int i = 0; i < pow(3, n - 1); i++) {
        for (int j = 0; j < permutatons[i].size(); j++) {
            cout << permutatons[i][j];
        }
        cout << endl;
        for (int j = 0; j < 2; j++) {
            get_shift(permutatons[i]);
        }
    }
}
int main() {
    int n;
    cin >> n;
    anti_gray(n);
}
