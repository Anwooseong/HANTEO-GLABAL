package num2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("sum = ");
        int sum = sc.nextInt();

        System.out.print("동전 배열 길이 = ");
        int N = sc.nextInt();

        System.out.println("동전의 종류 입력");
        int[] coins = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            coins[i] = sc.nextInt();
        }

        int[] dp = new int[sum + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - coins[i] > 0) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                } else if (j - coins[i] == 0) {
                    dp[j]++;
                }
            }
        }
        System.out.println(dp[sum]);
    }
}
