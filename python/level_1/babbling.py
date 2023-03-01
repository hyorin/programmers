nounce = ["aya", "ye", "woo", "ma"]


def solution(babbling):
    answer = 0

    for word in babbling:
        for canSay in nounce:
            word = word.replace(canSay, " ", 1)
        answer += 1 if len(word.strip()) == 0 else 0
    return answer


print(solution(["aya", "yee", "u", "maa", "wyeoo", "yemaye"])) # result = 1
