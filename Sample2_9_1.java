public class Sample2_9_1 {                                   // 宣告公開類別 Sample2_9（檔名需與類別同名）
    private static final int LOOP_COUNT = 5;               // 定義常數（constant）：總迴圈次數，集中管理便於日後調整

    public static void main(String[] args) {               // 主程式進入點（JVM 從這裡開始執行）
        for (int i = 1; i <= LOOP_COUNT; i++) {            // for 迴圈（for loop）：從 1 執行到 LOOP_COUNT（含）
            System.out.printf("第%d次的迴圈%n", i);        // 格式化輸出（formatted output）：%d 代入數字，%n 跨平台換行
        }                                                   // for 迴圈結束
        System.out.println("迴圈結束");                    // 告知使用者迴圈流程已完成
    }                                                       // main 方法結束
}                                                           // 類別 Sample2_9 結束

