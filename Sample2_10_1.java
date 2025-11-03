public class Sample2_10_1 {                                   // 宣告公開類別 Sample2_10_1（檔名需與類別同名）
    private static final int LOOP_COUNT = 5;                   // 定義常數：要執行的總迴圈次數（集中管理、易於調整）

    public static void main(String[] args) {                   // 主程式進入點（JVM 從這裡開始執行）
        int i = 1;                                             // 迴圈計數器起始值設為 1
        while (i <= LOOP_COUNT) {                              // 當 i 小於等於迴圈上限時持續執行
            System.out.printf("第%d次的迴圈%n", i);            // 使用格式化輸出：%d 代入 i，%n 為跨平台換行
            i++;                                               // 每次迴圈結束前將計數器 i 遞增 1
        }                                                      // while 迴圈結束
        System.out.println("迴圈結束");                        // 提示使用者迴圈流程已完成
    }                                                          // main 方法結束
}                                                              // 類別 Sample2_10_1 結束
