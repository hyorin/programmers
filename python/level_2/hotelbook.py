"""문제 설명

호텔을 운영 중인 코니는 최소한의 객실만을 사용하여 예약 손님들을 받으려고 합니다. 한 번 사용한 객실은 퇴실 시간을 기준으로 10분간 청소를 하고 다음 손님들이 사용할 수 있습니다.
예약 시각이 문자열 형태로 담긴 2차원 배열 book_time이 매개변수로 주어질 때, 코니에게 필요한 최소 객실의 수를 return 하는 solution 함수를 완성해주세요."""
minute_per_hour = 60
minute_per_day = minute_per_hour * 24 # 하루를 분 단위로 쪼갤 경우 배열 수 
cleaning = 10 # 청소에 걸리는 시간(분)

reservation = [0 for i in range(minute_per_day)]
def solution(book_time):
    for start_time, end_time in book_time:
        start_time_hour, start_time_minute = start_time.split(":")
        start_index = int(start_time_hour) * minute_per_hour + int(start_time_minute)
        reservation[start_index] += 1

        end_time_hour, end_time_minute = end_time.split(":")
        end_index = int(end_time_hour) * minute_per_hour + int(end_time_minute)
        reservation[end_index if end_index + cleaning >= minute_per_day else end_index + cleaning] -= 1

    for i, value in enumerate(reservation):
        if i == 0: continue
        reservation[i] = value + reservation[i-1]
    return max(reservation)

print(solution([["15:00", "17:00"], ["16:40", "18:20"], ["14:20", "15:20"], ["14:10", "19:20"], ["18:20", "21:20"]]))