def calculate_area(element, stack, index):
    start_index = -1 if len(stack) == 0 else stack[len(stack) - 1][1]
    return element * (index - start_index - 1)


N = int(input())
heights = list(map(int, input().split()))
heights.append(0)

stack = []
area = 0
for index in range(len(heights)):
    while len(stack) != 0 and heights[index] < stack[len(stack) - 1][0]:
        data = stack.pop()
        area = max(area, calculate_area(data[0], stack, index))
    stack.append((heights[index], index))

print(area)
