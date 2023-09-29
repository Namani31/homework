import java.util.InputMismatchException;
import java.util.Scanner;

class userException extends Exception {
    public userException() {
        super();
    }
}

public class howeworkApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] studentScore = new int[5];
        int scoreTotal = 0;

        System.out.println("===============================");
        System.out.println("***** 성적 입력 확인 프로그램 *****");
        System.out.println("===============================");

        while (true) {
            System.out.print("성적 입력 (1) / 평균 확인 (2) / 종료 (0): ");
            int menuSelect = sc.nextInt();

            switch (menuSelect) {
                case 1: // 성적 입력
                    inputScores(studentScore, sc);
                    scoreTotal = calculateTotal(studentScore);
                    break;
                case 2: // 성적 출력
                    printScores(studentScore, scoreTotal);
                    break;
                case 0: // 종료
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default: // 그 외 번호 입력
                    System.out.println("1번, 2번, 0번을 입력하세요.");
            }
        }
    }

    // 성적 입력을 처리하는 메소드
    private static void inputScores(int[] studentScore, Scanner sc) {
        for (int scoreInput = 0; scoreInput < studentScore.length; scoreInput++) {
            boolean validInput = false; // 예외 처리 반복을 위한 변수, 올바른 입력 여부 나타냄
            System.out.printf("성적 %d 입력: ", scoreInput + 1);

            while (!validInput) {
                try {
                    studentScore[scoreInput] = sc.nextInt();

                    if (studentScore[scoreInput] < 0 || studentScore[scoreInput] > 100) {
                        throw new userException();
                    }
                    validInput = true; // 올바른 범위 내의 입력을 받았을 때 예외 반복 종료
                } catch (userException e) {
                    System.out.println("성적 입력 오류!! 0~100 사이의 값으로 다시 입력하세요.");
                    System.out.printf("성적 %d 입력: ", scoreInput + 1);
                } catch (InputMismatchException e) {
                    System.out.println("성적 입력 오류!! 숫자로 다시 입력하세요.");
                    System.out.printf("성적 %d 입력: ", scoreInput + 1);
                    sc.next();  // 무한 루프 방지 위해 입력 스트림 비움
                }
            }
        }
    }

    // 성적의 총합을 계산하는 메소드
    private static int calculateTotal(int[] studentScore) {
        int addSum = 0;
        for (int grade : studentScore) {
            addSum += grade;
        }
        return addSum;
    }

    // 성적 출력 및 평균 계산을 처리하는 메소드
    private static void printScores(int[] studentScore, int scoreTotal) {
        System.out.println("\n입력된 점수");
        for (int printGrade : studentScore) {
            System.out.println(printGrade);
        }
        double scoreAvg = (double) scoreTotal / studentScore.length;
        System.out.printf("평균: %.1f\n", scoreAvg);
    }
}