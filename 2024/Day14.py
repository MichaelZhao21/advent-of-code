import re
from collections import namedtuple

ir = re.compile(r'p=(.*) v=(.*)')
Rob = namedtuple('Rob', ['x', 'y', 'dx', 'dy'])

f = open('day14.in')
ss = f.readlines()

w = 101
h = 103

# Part 1
robs = []
for s in ss:
    m = ir.search(s)
    a = list(map(lambda x: int(x), m.group(1).split(',')))
    b = list(map(lambda x: int(x), m.group(2).split(',')))
    robs.append(Rob(a[0], a[1], b[0], b[1]))

g = [[0] * w for _ in range(h)]
for r in robs:
    x = (r.x + 100 * r.dx) % w
    y = (r.y + 100 * r.dy) % h
    g[y][x] += 1

q = {
    (False, False): 0,
    (False, True): 0,
    (True, False): 0,
    (True, True): 0
}
hm = h // 2
wm = w // 2
for i, r in enumerate(g):
    for j, c in enumerate(r):
        if c == 0 or i == hm or j == wm:
            continue
        q[(i > hm, j > wm)] += c
total = 1
for i in q:
    total *= q[i]
print(total)

# Part 2
# NOTE: Pipe this output to a file and look for long line of robots (more than 10 X's)
#       This will take ~30 secs to generate the day14.out file
secs = 0
while secs < w * h:
    secs += 1
    print("\n\n\n")
    print('=====[', secs, ']=====')
    
    g = [[0] * w for _ in range(h)]
    for i, r in enumerate(robs):
        x = (r.x + r.dx) % w
        y = (r.y + r.dy) % h
        g[y][x] += 1
        robs[i] = Rob(x, y, r.dx, r.dy)

    for r in g:
        for c in r:
            print('.' if c == 0 else 'X', end='')
        print()
