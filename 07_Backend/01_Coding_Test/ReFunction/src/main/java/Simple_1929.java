import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * packageName : PACKAGE_NAME
 * fileName : Simple_1929
 * author : GGG
 * date : 2024-03-08(008)
 * description : 백준  1929
 * 요약 :
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-08(008)         GGG          최초 생성
 */
public class Simple_1929 {
    public static void main(String[] args) throws Exception {

//    시간 초과 : 1) BufferedReader (문자열로만 받음)
//              2) StringBuilder
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = bufferedReader.readLine().split(" "); // 3 16
        int start = Integer.parseInt(str[0]);   // 3
        int end = Integer.parseInt(str[1]);     // 16

        StringBuilder stringBuilder = new StringBuilder();  // 시간초과 해결
        int[] arr = new int[1000001];                       // 숫자배열
//        숫자배열 초기화 : 2 ~ 1000000 까지 배열에 넣기  // 배열의 인덱스번호,
//        2가 들어가면, 소수면 true, 4가 들어가면 소수가 아니니 false
//        그 배열을 판단하는게 배열의 값
        for (int i = 2; i < 1000000; i++) {
//            arr[0] == arr[1] 에는 0이 들어가있음, 그래서 소수 아님
            arr[i] = i;         // 소수이면 그 값 그대로 넣기, 아니면 0으로 넣기
        }
//        에라토스테네스 체 반복문 실행 (2중 반복문)
//        배수들은 소수아님 => 배수면 0넣기
        for (int i = 2; i < 1000000; i++) {
//                소수판단 반복문 : 배수들만 판단. 2*i
            for (int j = 2 * i; j < 1000000; j += i) {
//                소수판단 조건문 : 나누었을 때 0이 되면 소수가 아님
//                배열값이 0이면 스킵
                if (arr[j] == 0) continue;
                if (j % i == 0) arr[j] = 0;   // 배수는 소수 아님 : 표시 (0저장)
            }
        }
//        결과 출력 : 소수인것만 출력 : 숫자배열에서 0보다 큰것만 출력
        for (int i = start; i <= end; i++) {
            if (arr[i] > 0) stringBuilder.append(i + "\n");
        }
        System.out.println(stringBuilder);
    }
}