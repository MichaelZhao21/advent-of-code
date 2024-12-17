f = open('day3.in')
s = f.readline()

# Part 1
hm = dict()
hm[(0, 0)] = 1
curr = (0, 0)
for c in s.strip():
    if c == '>':
        curr = (curr[0]+1, curr[1])
    elif c == 'v':
        curr = (curr[0], curr[1]+1)
    elif c == '<':
        curr = (curr[0]-1, curr[1])
    else:
        curr = (curr[0], curr[1]-1)
    
    if curr not in hm:
        hm[curr] = 0
    hm[curr] += 1
print(len(hm))

# Part 2
hm = dict()
hm[(0, 0)] = 1
a = (0, 0)
b = (0, 0)
turn = 0
for c in s.strip():
    curr = a if turn % 2 == 0 else b
    if c == '>':
        curr = (curr[0]+1, curr[1])
    elif c == 'v':
        curr = (curr[0], curr[1]+1)
    elif c == '<':
        curr = (curr[0]-1, curr[1])
    else:
        curr = (curr[0], curr[1]-1)

    if curr not in hm:
        hm[curr] = 0
    hm[curr] += 1
    if turn % 2 == 0:
        a = curr
    else:
        b = curr
    turn += 1
print(len(hm))
