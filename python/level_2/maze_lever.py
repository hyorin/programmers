'''
1 x 1 크기의 칸들로 이루어진 직사각형 격자 형태의 미로에서 탈출하려고 합니다. 각 칸은 통로 또는 벽으로 구성되어 있으며, 벽으로 된 칸은 지나갈 수 없고 통로로 된 칸으로만 이동할 수 있습니다. 통로들 중 한 칸에는 미로를 빠져나가는 문이 있는데, 이 문은 레버를 당겨서만 열 수 있습니다. 레버 또한 통로들 중 한 칸에 있습니다. 따라서, 출발 지점에서 먼저 레버가 있는 칸으로 이동하여 레버를 당긴 후 미로를 빠져나가는 문이 있는 칸으로 이동하면 됩니다. 이때 아직 레버를 당기지 않았더라도 출구가 있는 칸을 지나갈 수 있습니다. 미로에서 한 칸을 이동하는데 1초가 걸린다고 할 때, 최대한 빠르게 미로를 빠져나가는데 걸리는 시간을 구하려 합니다.

미로를 나타낸 문자열 배열 maps가 매개변수로 주어질 때, 미로를 탈출하는데 필요한 최소 시간을 return 하는 solution 함수를 완성해주세요. 만약, 탈출할 수 없다면 -1을 return 해주세요.

제한사항
5 ≤ maps의 길이 ≤ 100
5 ≤ maps[i]의 길이 ≤ 100
maps[i]는 다음 5개의 문자들로만 이루어져 있습니다.
S : 시작 지점
E : 출구
L : 레버
O : 통로
X : 벽
시작 지점과 출구, 레버는 항상 다른 곳에 존재하며 한 개씩만 존재합니다.
출구는 레버가 당겨지지 않아도 지나갈 수 있으며, 모든 통로, 출구, 레버, 시작점은 여러 번 지나갈 수 있습니다.
'''


def solution(maps):
    start_x, start_y = None, None
    lever_x, lever_y = None, None
    end_y, end_y = None, None

    # 시작 위치와 출구 위치 찾기
    for i in range(len(maps)):
        for j in range(len(maps[0])):
            if maps[i][j] == "S":
                start_x, start_y = i, j
            elif maps[i][j] == "L":
                lever_x, lever_y = i, j
            elif maps[i][j] == "E":
                end_y, end_y = i, j

    # BFS로 경로 찾기 (start - lever)
    visited = [[False] * len(maps[0]) for _ in range(len(maps))]
    path = [(start_x, start_y)]
    if not bfs(maps, visited, start_x, start_y, lever_x, lever_y, path):
        return -1

    # BFS로 경로 찾기 (lever - end)
    visited = [[False] * len(maps[0]) for _ in range(len(maps))]
    if not bfs(maps, visited, lever_x, lever_y, end_y, end_y, path):
        return -1

    print("Path found:")
    for i, (row, col) in enumerate(path):
        print(f"Step {i}: ({row}, {col})")
    return len(path)


def bfs(maps, visited, start_x, start_y, end_x, end_y, path):
    if (start_x, start_y) == (end_x, end_y):
        return True  # 출구에 도착한 경우

    visited[start_x][start_y] = True

    # 인접한 칸을 탐색합니다.
    for direction_x, direction_y in ((-1, 0), (1, 0), (0, -1), (0, 1)):
        next_x, next_y = start_x + direction_x, start_y + direction_y
        if 0 <= next_x < len(maps) and 0 <= next_y < len(maps[0]) and maps[next_x][next_y] != "X" and not visited[next_x][next_y]:
            path.append((next_x, next_y))
            if bfs(maps, visited, next_x, next_y, end_x, end_y, path):
                return True
            path.pop()  # 탐색 실패시 해당 노드에서의 경로 삭제

    return False

solution(["SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"])
