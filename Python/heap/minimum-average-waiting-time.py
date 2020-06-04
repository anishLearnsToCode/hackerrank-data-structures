from heapq import heappush, heappop

tasks = []

N = int(input())

for _ in range(N):
    arrival, cook_time = map(int, input().split())
    tasks.append((arrival, cook_time))

tasks.sort(reverse=True)

pq = []
time_waiting = 0
current_time = 0

while tasks or pq:
    while tasks and tasks[-1][0] <= current_time:
        heappush(pq, tasks.pop()[::-1])
    if pq:
        current_task = heappop(pq)
        current_time += current_task[0]
        time_waiting += current_time - current_task[1]
    else:
        heappush(pq, tasks.pop()[::-1])
        current_time = pq[0][1]

print(time_waiting // N)
