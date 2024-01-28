n, k = map(int, input().split())
 
dp = []
for i in range(2*n + 4):
    dp.append([0]*(2*n + 4))
 
dp[0][0] = 1
for i in range(n * 2 + 2):
    for j in range(2 * n + 2):
        if j < n:
            dp[i + 1][j + 1] += dp[i][j]
        if j > 0:
            dp[i + 1][j - 1] += dp[i][j]
len = 0
ans = ""
num = k
for i in range(n*2 - 1, -1, -1):
    if len < n and dp[i][len + 1] > num:
        ans = ans + '('
        len = len + 1
    else:
        ans = ans + ')'
        if len < n:
            num = num - dp[i][len + 1]
        len = len - 1
 
print(ans)
