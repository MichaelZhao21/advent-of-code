f = open('day8.in')
g = list(map(lambda x: x.replace('\n', ''), f.readlines()))
n = len(g)

# Part 1
freqs = dict()
for i, w in enumerate(g):
    for j, u in enumerate(w):
        if u == '.':
            continue
        if u not in freqs:
            freqs[u] = set()
        freqs[u].add((i, j))

v = [[False]*n for i in range(n)]
c = 0
for k in freqs:
    fl = list(freqs[k])
    for i in range(len(fl)):
        a = fl[i]
        for j in range(i+1, len(fl)):
            b = fl[j]
            d = (a[0] - b[0], a[1] - b[1])
            ad = (a[0] + d[0], a[1] + d[1])
            bd = (b[0] - d[0], b[1] - d[1])
            if ad[0] >= 0 and ad[0] < n and ad[1] >= 0 and ad[1] < n and not v[ad[0]][ad[1]]:
                v[ad[0]][ad[1]] = True
                c += 1
            if bd[0] >= 0 and bd[0] < n and bd[1] >= 0 and bd[1] < n and not v[bd[0]][bd[1]]:
                v[bd[0]][bd[1]] = True
                c += 1
print(c)

# Part 2
v = [[False]*n for i in range(n)]
c = 0
freqs = dict()
for i, w in enumerate(g):
    for j, u in enumerate(w):
        if u == '.':
            continue
        if u not in freqs:
            freqs[u] = set()
        freqs[u].add((i, j))
        v[i][j] = True
        c += 1

for k in freqs:
    fl = list(freqs[k])
    for i in range(len(fl)):
        a = fl[i]
        for j in range(i+1, len(fl)):
            b = fl[j]
            d = (a[0] - b[0], a[1] - b[1])

            ad = (a[0] + d[0], a[1] + d[1])
            bd = (b[0] - d[0], b[1] - d[1])
            while ad[0] >= 0 and ad[0] < n and ad[1] >= 0 and ad[1] < n:
                if not v[ad[0]][ad[1]]:
                    v[ad[0]][ad[1]] = True
                    c += 1
                ad = (ad[0] + d[0], ad[1] + d[1])
            while bd[0] >= 0 and bd[0] < n and bd[1] >= 0 and bd[1] < n:
                if not v[bd[0]][bd[1]]:
                    v[bd[0]][bd[1]] = True
                    c += 1
                bd = (bd[0] - d[0], bd[1] - d[1])

print(c)