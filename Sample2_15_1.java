public class Sample2_15_1 {                                              // 宣告公開類別 Sample2_15（檔名需與類別同名）
    private static final int[] SCORES = {80, 60, 22, 50, 75};          // 以陣列常值直接初始化分數資料，避免先宣告再逐一指定

    public static void main(String[] args) {                           // 主程式進入點（JVM 從這裡開始執行）
        for (int i = 0; i < SCORES.length; i++) {                      // 迴圈由 0 執行到 SCORES.length-1，使用 length 避免魔術數字
            System.out.printf("第%d個人的分數是%d分%n", i + 1, SCORES[i]); // 使用格式化輸出：第(索引+1)位的分數；%n 為跨平台換行
        }                                                              // for 迴圈結束
    }                                                                  // main 方法結束
}                                                                      // 類別 Sample2_15 結束
