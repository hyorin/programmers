"""
둘만의 암호

두 문자열 s와 skip, 그리고 자연수 index가 주어질 때, 다음 규칙에 따라 문자열을 만들려 합니다. 암호의 규칙은 다음과 같습니다.
- 문자열 s의 각 알파벳을 index만큼 뒤의 알파벳으로 바꿔줍니다.
- index만큼의 뒤의 알파벳이 z를 넘어갈 경우 다시 a로 돌아갑니다.
- skip에 있는 알파벳은 제외하고 건너뜁니다.

제한사항
- 5 ≤ s의 길이 ≤ 50
- 1 ≤ skip의 길이 ≤ 10
- s와 skip은 알파벳 소문자로만 이루어져 있습니다.
  skip에 포함되는 알파벳은 s에 포함되지 않습니다.
- 1 ≤ index ≤ 20
"""
minUnicode = ord('a')
maxUnicode = ord('z')
alphabets = [chr(unicode) for unicode in range(minUnicode, maxUnicode + 1)]

def shiftAlphabet(target, skip, shiftCount):
    targetUnicode = ord(target) # a~z 사이, 97~122
    index = targetUnicode - minUnicode
    find = 0
    while find < shiftCount:
        index = 0 if index + 1 >= len(alphabets) else index + 1
        if alphabets[index] in skip:
            continue
        find+=1
    return alphabets[index]

def solution(s, skip, index):
    answer = [shiftAlphabet(c, skip, index) for c in s]
    return ''.join(answer)

print(solution("aukks", "wbqd", 5))