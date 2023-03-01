#머쓱이는 구슬을 친구들에게 나누어주려고 합니다. 구슬은 모두 다르게 생겼습니다. 머쓱이가 갖고 있는 구슬의 개수 balls와 친구들에게 나누어 줄 구슬 개수 share이 매개변수로 주어질 때, balls개의 구슬 중 share개의 구슬을 고르는 가능한 모든 경우의 수를 return 하는 solution 함수를 완성해주세요.
# 1<= balls <=30
# 1<= share <=30
# share <= balls
def factorial(num):
	if num == 1:
		return 1
	returnVal = 1
	for n in range(num):
		returnVal *= (n+1)
	return returnVal

def solution(balls, share):
	answer = factorial(balls) / (factorial(balls - share) * factorial(share))
	return answer

print(solution(5,3))