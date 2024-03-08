/**
 * packageName : PACKAGE_NAME
 * fileName : Euclid
 * author : GGG
 * date : 2024-03-08(008)
 * description : 유클리드 호제법
 * 요약 :
 *   약수 : 어떤 수를 나누었을 때 0으로 떨어지는 수 (ex. 8의 약수 : 1, 2, 4, 8)
 *   배수 : 어떤 수를 나누었을 때 0으로 떨어지는 수, 자기자신보다 증가하는 수(ex. 8의 배수 : 8, 16, ...)
 *   공약수 : 공통된 약수
 *     -> 4의 약수 : 1, 2, 4
 *     -> 8의 약수 : 1, 2, 4, 8
 *     -> 공약수 : 1, 2, 4
 *     -> 최대공약수 : 4
 *   공배수 : 공통된 배수
 *     -> 4의 배수 : 4, 8, 12, 16, ...
 *     -> 8의 배수 : 8, 16, 24, 32, ...
 *     -> 공배수 : 8, 16, 24, 32, ...
 *     -> 최소공배수 : 8
 *      * 유클리드 호제법 :
 *       1) a % b = r,  r이 0이 아니면
 *          b % r = r', r이 0이 되면, r이 최대공약수가 된다.
 *          ex) 11 % 3 = 2, 3 % 2 = 1, 2 % 1 = 0 : 2와 1의 최대공약수는 1
 *         => 8 % 4 = 0 => 최대공약수 : 4
 *         => (8 * 4) / 최대공약수(4) => 최소공배수 : 8

 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-08(008)         GGG          최초 생성
 */
public class Euclid {
//    정적함수(전역함수) : static 함수
//    유클리드 호제법 알고리즘 이용 : 최대공약수 구하는 함수
    public static int gcd(int a, int b) {
//        최초 1번 나머지 구하기 : r
        int r = a % b;
        while(r > 0){
//            변수값 바꾸기 : a에는 b, b에는 r 넣어서 재계산
            a = b;
            b = r;
            r = a % b;  // 다시 나머지 구하기
        }
        return b;   // 최대공약수
    }
    public static void main(String[] args) {
//        실행 : 8, 4의 최대공약수 / 최소공배수 구하기
        int gcdVal = gcd(8,4);        // 최대공약수
        int lcmVal = (8*4)/gcdVal;          // 최소공배수

//        출력
        System.out.println(gcdVal);
    }
}
