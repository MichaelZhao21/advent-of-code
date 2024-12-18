f = open('day18.in')
cl = list(map(lambda x: tuple(map(lambda y: int(y), x.split(","))), f.readlines()))

n = 71

# Part 1
g = [ [0]*n for i in range(n)]
b = 1024
for i, v in enumerate(cl):
    if i == b:
        break
    g[v[0]][v[1]] = 1

v = [ [False]*n for i in range(n)]
q = [(0, 0, 0)]
win = None
while True:
    c = q.pop(0)
    if c[0] == n-1 and c[1] == n-1:
        win = c
        break
    if c[0] != 0 and g[c[0]-1][c[1]] == 0 and not v[c[0]-1][c[1]]:
        v[c[0]-1][c[1]] = True
        q.append((c[0]-1, c[1], c[2]+1))
    if c[0] != n-1 and g[c[0]+1][c[1]] == 0 and not v[c[0]+1][c[1]]:
        v[c[0]+1][c[1]] = True
        q.append((c[0]+1, c[1], c[2]+1))
    if c[1] != 0 and g[c[0]][c[1]-1] == 0 and not v[c[0]][c[1]-1]:
        v[c[0]][c[1]-1] = True
        q.append((c[0], c[1]-1, c[2]+1))
    if c[1] != n-1 and g[c[0]][c[1]+1] == 0 and not v[c[0]][c[1]+1]:
        v[c[0]][c[1]+1] = True
        q.append((c[0], c[1]+1, c[2]+1))

if win == None:
    print('WHATTTTTTTTTTTTTTTTTTTTT NO SOLUTION?!?!!')
print(win[2])

# Part 2
g = [[0]*n for i in range(n)]
for i, t in enumerate(cl):
    g[t[0]][t[1]] = 1
    v = [[False]*n for i in range(n)]
    q = [(0, 0, 0)]
    win = None
    while not len(q) == 0:
        c = q.pop(0)
        if c[0] == n-1 and c[1] == n-1:
            win = c
            break
        if c[0] != 0 and g[c[0]-1][c[1]] == 0 and not v[c[0]-1][c[1]]:
            v[c[0]-1][c[1]] = True
            q.append((c[0]-1, c[1], c[2]+1))
        if c[0] != n-1 and g[c[0]+1][c[1]] == 0 and not v[c[0]+1][c[1]]:
            v[c[0]+1][c[1]] = True
            q.append((c[0]+1, c[1], c[2]+1))
        if c[1] != 0 and g[c[0]][c[1]-1] == 0 and not v[c[0]][c[1]-1]:
            v[c[0]][c[1]-1] = True
            q.append((c[0], c[1]-1, c[2]+1))
        if c[1] != n-1 and g[c[0]][c[1]+1] == 0 and not v[c[0]][c[1]+1]:
            v[c[0]][c[1]+1] = True
            q.append((c[0], c[1]+1, c[2]+1))

    if win == None:
        print(",".join(map(lambda x: str(x), t)))
        break
